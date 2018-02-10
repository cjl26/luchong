package com.gzyct.m.api.busi.handlers.hicatcard;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.QRCodeGenerateReq;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.QRCodeGenerateResp;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.WxACodeUnlimitReq;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.WxTokenResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.TWxToken;
import com.gzyct.m.api.busi.db.service.hicatcard.WxTokenService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.HttpRequestUtil;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//https://mp.weixin.qq.com/debug/wxadoc/dev/api/qrcode.html
//https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183

@Component
public class QRCodeGenerateBizHandler extends DefaultBizParamChecker<QRCodeGenerateReq, QRCodeGenerateResp> implements BizHandler<QRCodeGenerateReq, QRCodeGenerateResp> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WxTokenService wxTokenService;

    @Value("${weixin.miniapp.appid}")
    String wxMinAppId;

    @Value("${weixin.miniapp.secret}")
    String wxMinAppSecret;

    @Override
    public QRCodeGenerateResp handle(QRCodeGenerateReq req) throws Exception {
        QRCodeGenerateResp bizResp = new QRCodeGenerateResp();
        ValidateRet vRet = checkParam(req, bizResp);
        if (!vRet.isValid()) {
            return bizResp;
        }

        try {
            //先獲取token
            List<TWxToken> wxTokenList = wxTokenService.findByEnable(true);
            TWxToken wxToken = null;
            boolean whetherToGetToken = false;
            //判斷是否過期
            if (wxTokenList == null || wxTokenList.size() == 0) {
                whetherToGetToken = true;
            } else {
                wxToken = wxTokenList.get(0);
                if (Long.valueOf(TimeUtil.getCurrTime()).longValue() > Long.valueOf(wxToken.getExpiresIn()).longValue() + Long.valueOf(wxToken.getUpdateTime()).longValue()) {
                    whetherToGetToken = true;
                }
            }

            //過期取微信拿token 并保存
            if (whetherToGetToken) {
                String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxMinAppId + "&secret=" + wxMinAppSecret;
                String jsonResp = HttpRequestUtil.getRequest(getTokenUrl);
                logger.info("jsonResp = " + jsonResp);
                WxTokenResp wxTokenResp = JSON.parseObject(jsonResp, WxTokenResp.class);

                if (jsonResp == null || jsonResp.isEmpty()) {
                    bizResp.setResult_code(BusiError.ERR_CODE_WX_TOKEN_ERROR);
                    bizResp.setError_message(BusiError.ERR_MSG_WX_TOKEN_ERROR);
                    return bizResp;
                }

                if (wxToken == null) {
                    wxToken = new TWxToken();
                    wxToken.setCreateTime(TimeUtil.getCurrTime());
                    wxToken.setEnable(true);
                }
                wxToken.setUpdateTime(TimeUtil.getCurrTime());
                wxToken.setToken(wxTokenResp.getAccess_token());
                wxToken.setExpiresIn(wxTokenResp.getExpires_in());
                wxTokenService.saveWxToken(wxToken);
            }

            //去請求二維碼
            String qrCodeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + wxToken.getToken();
            logger.info("qrCodeUrl = " + qrCodeUrl);

            WxACodeUnlimitReq wxACodeUnlimitReq = new WxACodeUnlimitReq();
            wxACodeUnlimitReq.setScene("111111111");
            wxACodeUnlimitReq.setPage("");//pages/card-detail/index?cardNumber=111111111
            wxACodeUnlimitReq.setWidth(req.getWidth());
            wxACodeUnlimitReq.setAuto_color(false);
            Map<String, Object> lineColorMap = new HashMap<String, Object>();
            lineColorMap.put("r", "0");
            lineColorMap.put("g", "0");
            lineColorMap.put("b", "0");
            wxACodeUnlimitReq.setLine_color(lineColorMap);
            String requestContent = JSON.toJSONString(wxACodeUnlimitReq);

            httpPostWithJSON(qrCodeUrl, requestContent);

            return bizResp;

        } catch (Exception ex) {
            logger.error("内部出错", ex);
            bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
            bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
            return bizResp;
        }


    }

    @Override
    public ValidateRet checkParam(QRCodeGenerateReq req, QRCodeGenerateResp resp) {
        ValidateRet vRet = super.checkParam(req, resp);
        if (!vRet.isValid()) return vRet;

        List<ValidateParam> paramList = new ArrayList<ValidateParam>();
        vRet = ValidateUtil.validate(paramList);

        if (vRet == null) vRet = new ValidateRet(true, "");
        if (!vRet.isValid()) {
            resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
            resp.setError_message(vRet.getErrMsg());
        }
        return vRet;
    }

    private void httpPostWithJSON(String url, String json)
            throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity se = new StringEntity(json);
        se.setContentType("application/json");
        se.setContentEncoding("UTF-8");
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                InputStream instreams = resEntity.getContent();
                logger.info("instreams =" + instreams);
                String uploadSysUrl = "D://test/";
                String fileName = TimeUtil.getCurrTime() + ".png";
                File saveFile = new File(uploadSysUrl + fileName);
                // 判断这个文件（saveFile）是否存在
                if (!saveFile.getParentFile().exists()) {
                    // 如果不存在就创建这个文件夹
                    saveFile.getParentFile().mkdirs();
                }
                File file = new File(uploadSysUrl + fileName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos = new FileOutputStream(file);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();

            }
        }
        httpPost.abort();
    }

}
