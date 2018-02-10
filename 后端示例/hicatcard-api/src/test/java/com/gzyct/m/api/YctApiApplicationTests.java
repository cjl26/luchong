//package com.gzyct.m.api;
//
//import com.alibaba.fastjson.JSON;
//import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryReq;
//import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryResp;
//import com.gzyct.m.api.busi.rest.BusiController;
//import com.gzyct.m.api.busi.util.*;
//import com.gzyct.m.api.busi.util.rsa.RSAUtil;
//import com.project.m.api.common.biz.req.BizRequest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import static org.hamcrest.core.Is.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ApplicationMain.class)
//@WebAppConfiguration
//public class YctApiApplicationTests {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private static String CHANNEL_CODE = "70000016";
//    private static String CHANNEL_KEY = "7a235f8368f5fa327bb6cac25d9c2fca";
//    public static final String pk = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAM6UHIgmAk8BGBL1+X0fj2hW7p70CtxkyhOU9aS3fCwZ63awtTJ2E3fJs2/rEfbSrgSb2EcK2LryFiIfFEkFGWECAwEAAQ==";
//    private MockMvc mockMvc;
//    private String testCardNo = "1111122222";
//
//    @Before
//    public void setupMockMvc() throws Exception {
////        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//        mockMvc  = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
////        BusiController busiController = new BusiController();
////        mockMvc = MockMvcBuilders.standaloneSetup(busiController).build();
//    }
//
//    public void doGet(String url){
//        System.out.println("http get url:"+url);
//        try {
//            String strResp = mockMvc.perform(post(url)
//                    .contentType(MediaType.APPLICATION_JSON_UTF8))
//                    .andDo(print())
//                    .andExpect(status().isOk())
////                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                    .andReturn().getResponse().getContentAsString();
//            ;
//            System.out.println("http get resp:"+strResp);
//            if (!strResp.equalsIgnoreCase("success")) {
//                assert false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void flows_test(){
//        testBannerQuery(1);
//    }
//
//    public AdvertisementQueryResp testBannerQuery(int place) {
//        AdvertisementQueryReq req = new AdvertisementQueryReq();
//        buildBaseParams(req);
//        req.setPlace(place);
//        req.setService("xiche.banner.query");
//        debuglog(JSON.toJSONString(req), req.getService());
//        AdvertisementQueryResp resp = JSON.parseObject(doPost(req), AdvertisementQueryResp.class);
//        return resp;
//    }
//
//    private String doPost(Object body) {
//        return doPost("/xicatcard/api",body);
//    }
//
//    private String doPost(String url, Object body) {
//        try {
////            System.out.println("mockMvc:"+mockMvc);
////            System.out.println("url:"+url);
////            System.out.println("body:"+body);
//            String strResp = mockMvc.perform(post(url)
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .content(JSON.toJSONString(body)))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                    .andExpect(jsonPath("$.result_code", is("0")))
//                    .andReturn().getResponse().getContentAsString();
//            return strResp;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /* util */
//    public void buildBaseParams(BizRequest baseParam) {
//        baseParam.setApi_version("1.0");
//        baseParam.setOpenid("123456");
//        baseParam.setCharset("UTF-8");
//        baseParam.setBrand("Brand-Test");
//        baseParam.setModel("Model-Test");
//        baseParam.setSystem("System-Test");
//        baseParam.setLanguage("Chinese");
//        baseParam.setPlatform("Plat-Test");
//        baseParam.setVersion("1.0");
//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String date = sDateFormat.format(new Date());
//        baseParam.setTimestamp(date);
//
//    }
//
//    public void debuglog(String req, String service) {
//        System.out.println(" ");
//        System.out.println("MockHttpServletRequest ");
//        System.out.println("      " + service);
//        System.out.println("      req   =============== " + req);
//        System.out.println("base64req   =============== " + Base64.encode(req, "UTF-8"));
//        System.out.println(" ");
//    }
//
//}
