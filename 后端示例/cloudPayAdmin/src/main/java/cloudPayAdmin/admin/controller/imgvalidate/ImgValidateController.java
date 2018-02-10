package cloudPayAdmin.admin.controller.imgvalidate;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.util.ImgValidateUtil;

@Controller
@RequestMapping("/cloudpay/imgValidate")
public class ImgValidateController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 生成图片验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/create")
	public void img(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = ImgValidateUtil.createCaptcha();
		BufferedImage buffImg = (BufferedImage) map.get("buffImg");
		String code = (String)map.get("systemRandomCode");
		HttpSession session = request.getSession();
		session.setAttribute("validateCode", code);
		try {
			// 将图像输出到Servlet输出流中。
			OutputStream sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 远程校验图片验证码
	 * @param validateCode
	 * @param request
	 * @return
	 */
	@RequestMapping("/validateCode")
	@ResponseBody
	public String validateCode(String validateCode,HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("validateCode");
		if(obj == null) {
			return "false";
		} else {
			String systemRandomCode = (String)obj;
			if(StringUtils.equalsIgnoreCase(systemRandomCode, validateCode)) {
				return "true";
			} else {
				return "false";
			}
		}
	}
}
