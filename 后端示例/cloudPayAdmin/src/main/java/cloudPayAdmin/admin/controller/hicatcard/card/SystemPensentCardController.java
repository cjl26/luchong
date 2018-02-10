package cloudPayAdmin.admin.controller.hicatcard.card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TSystemPresentCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUser;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCardTransfer;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VSystemPresentCard;
import cloudPayAdmin.admin.service.hicatcard.card.CardService;
import cloudPayAdmin.admin.service.hicatcard.card.SystemPresentCardService;
import cloudPayAdmin.admin.service.hicatcard.user.UserCardService;
import cloudPayAdmin.admin.service.hicatcard.user.UserCardTransferService;
import cloudPayAdmin.admin.service.hicatcard.user.UserService;
import cloudPayAdmin.constant.Constant;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.AdminUserInfoUtil;
import cloudPayAdmin.util.HttpUtil;
import cloudPayAdmin.util.IdGenerator;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.WxApiUtil;
import cloudPayAdmin.util.ZipUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/systempensentcard")
public class SystemPensentCardController {
	
	@Autowired
	SystemPresentCardService systemPresentCardService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserCardService userCardService;
	
	@Autowired
	UserCardTransferService userCardTransferService;
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("userCardStatusMap", TUserCard.initStatusMap());
		request.setAttribute("statusMap", TSystemPresentCard.initStatusMap());
		return "hicatcard/card/system-present-card-list";
	}
	
	@RequestMapping("/getSystemPensentCardListData")
	@ResponseBody
	public String getCardListData(HttpServletRequest request) {		
		
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VSystemPresentCard.class, request);
		PageBean<VSystemPresentCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		for(VSystemPresentCard presentCard : cloudpayCardPage.getContent()) {
			String qrCodePath = StringUtils.contains(presentCard.getQrCodePath(), env.getProperty("image.upload.database.suffix.path")) ? presentCard.getQrCodePath() : env.getProperty("image.upload.database.suffix.path")+presentCard.getQrCodePath();
			presentCard.setQrCodePath(env.getProperty("image.http.base.path") + qrCodePath);
			
			List<TUserCardTransfer> userCardTransferList = userCardTransferService.findBySourceCardNumber(presentCard.getCardNumber());
			if(!CollectionUtils.isEmpty(userCardTransferList)) {
				
				TUserCardTransfer userCardTransfer = userCardTransferList.get(0);
				
				if(userCardTransfer != null) {
					TUserCard userCard = userCardService.findById(userCardTransfer.getTargetUserCardId());
					if(userCard != null) {
						presentCard.setReceiverStatus(userCard.getStatus());
						presentCard.setReceiverEndTime(userCard.getEndTime());
						presentCard.setReceiverUserCardNumber(userCard.getUserCardNumber());
						TUser user = userService.findById(userCard.getUserId());
						if(user != null) {
							presentCard.setReceiverNickname(user.getNickname());
							presentCard.setReceiverPhone(user.getPhone());
						}
					}				
				}
			}			
		}
		
		AdminLteDataTableResp<VSystemPresentCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(VSystemPresentCard.class);
	}
	
	
	@RequestMapping("/toBatchMakeCardPage")
	public String toBatchMakeCardPage(HttpServletRequest request) {
		List<TCard> cardList = cardService.findAll();
		request.setAttribute("cardList", cardList);
		return "hicatcard/card/batch-make-card";
	}
	
	/**
	 * 批量制卡方法
	 * @param cardId   卡片标识
	 * @param amount   制卡的数量
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/batchMakeCard")
	@ResponseBody
	public String batchMakeCard(Long cardId,Integer amount) throws IOException {
		String downloadUrl = "";
		if(amount != null && amount > 0) {
			List<TSystemPresentCard> systemPresentCardList = new ArrayList<TSystemPresentCard>();
			
			List<File> qrCodeFiles = new ArrayList<File>();
			List<String> zipEntryNames = new ArrayList<String>();
			
			//获得目前最大的批次数
			Long maxBatch = systemPresentCardService.getMaxBacth();
			Long thisBatch = null;
			if(maxBatch == null) {
				thisBatch = 1L;
			} else {
				thisBatch = maxBatch + 1;
			}
			
			for(int i=0; i<amount; i++) {
				TSystemPresentCard systemPresentCard = new TSystemPresentCard();
				systemPresentCard.setCardId(cardId);							
				systemPresentCard.setBatch(thisBatch);				
				String cardNumber = IdGenerator.generateSystemCardId(IdGenerator.ORDERID_PREFIX_SYSTEM_CARD, "00", "0000");
				systemPresentCard.setCardNumber(cardNumber);
				systemPresentCard.setStatus(BaseEntity.STATUS_IN_USE);
				systemPresentCard.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
				systemPresentCard.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				systemPresentCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				
				String databasePath = env.getProperty("image.upload.database.suffix.path") + Constant.PRESENTCARDQRCODE_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + cardId + "_" + System.currentTimeMillis() + ".png";
				File destination = new File(env.getProperty("image.upload.base.path") + StringUtils.replaceEach(databasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator}));
				
				FileUtils.copyInputStreamToFile(WxApiUtil.getUnLimitQrCode(cardNumber, "pages/card-detail/index"), destination);
				
				systemPresentCard.setQrCodePath(databasePath);
				systemPresentCardList.add(systemPresentCard);
				qrCodeFiles.add(destination);
				zipEntryNames.add(cardNumber + ".png");
			}
			
			List<TSystemPresentCard> sysPresentCards =systemPresentCardService.saveList(systemPresentCardList);
			
			String zipFileName = Constant.PRESENTCARDQRCODE_PIC_FILE_PATH + File.separator + TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR) + "_" + sysPresentCards.size() + ".zip";
			String zipPath = env.getProperty("image.upload.base.path") + zipFileName;
			ZipUtil.toZip(qrCodeFiles, zipEntryNames, new FileOutputStream(new File(zipPath)));
			downloadUrl = env.getProperty("image.http.base.path") + env.getProperty("image.upload.database.suffix.path") + StringUtils.replace(zipFileName, File.separator, "/");
 			
		}
		return downloadUrl;
	}
	
	/**
	 * 选定 id，批量导出二维码
	 * @param request
	 * @return
	 * @throws RuntimeException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/batchExportQrCode")
	public String batchExportQrCode(HttpServletRequest request) throws FileNotFoundException, RuntimeException {
		String systemCardIds = request.getParameter("systemCardIds");
		String[] systemCardIdList = null;
		if(StringUtils.isNotBlank(systemCardIds)) {
			systemCardIdList = StringUtils.splitByWholeSeparator(systemCardIds, ",");
			List<Long> ids = new ArrayList<Long>();
			for(String id : systemCardIdList) {
				ids.add(Long.parseLong(id));
			}
			
			List<TSystemPresentCard> systemPresentCardList = systemPresentCardService.findByIds(ids);
			List<File> qrCodeFiles = new ArrayList<File>();
			List<String> zipEntryNames = new ArrayList<String>();
			for(TSystemPresentCard systemCard : systemPresentCardList) {
				File qrCodeFile = new File(env.getProperty("image.upload.base.path") + StringUtils.replace(StringUtils.replace(systemCard.getQrCodePath(), HttpUtil.HTTP_PATH_SEPERATOR, File.separator), env.getProperty("image.upload.database.suffix.path"), ""));
				String zipEntryName = systemCard.getCardNumber() + ".png";
				qrCodeFiles.add(qrCodeFile);
				zipEntryNames.add(zipEntryName);
			}
			
			String zipFileName = Constant.PRESENTCARDQRCODE_PIC_FILE_PATH + File.separator + TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR) + "_" + systemPresentCardList.size() + ".zip";
			String zipPath = env.getProperty("image.upload.base.path") + zipFileName;
			ZipUtil.toZip(qrCodeFiles, zipEntryNames, new FileOutputStream(new File(zipPath)));
			String downloadUrl = env.getProperty("image.http.base.path") +  env.getProperty("image.upload.database.suffix.path") + StringUtils.replace(zipFileName, File.separator, "/");
			return "redirect:" + downloadUrl;
		} else {
			return "redirect:/hicatcard/systempensentcard/toListPage";
		}
	}
}
