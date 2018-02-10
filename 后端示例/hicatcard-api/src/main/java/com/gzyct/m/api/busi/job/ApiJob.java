package com.gzyct.m.api.busi.job;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.util.HttpRequestUtil;
import com.gzyct.m.api.busi.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
@EnableScheduling
public class ApiJob {

    protected  Logger logger = LoggerFactory.getLogger(getClass());
    private static long httpGetCount = 0;
    private static long httpGetFailCount = 0;
    private static long  httpGetUsedTime = 0;

    private static long httpPostCount = 0;
    private static long httpPostFailCount = 0;
    private static long  httpPostUsedTime = 0;
    private static String  BASE_URL_SERVER = "https://hicatcitycard.6so2o.com";
    private static String  BASE_URL_LOCAL = "http://127.0.0.1:8086";

    @Scheduled(cron = "${corn:0 0/1 * * * ?}")
    public void api1() throws Exception {
        logger.info(TimeUtil.getCurrTime());
    }

//    @Scheduled(cron = "${corn:0/10 * * * * ?}")
    public void job2() throws Exception {

        long startGet = System.currentTimeMillis();
        String getResp = HttpRequestUtil.getRequest(BASE_URL_LOCAL+"/xicatcard/codeversion");
//        logger.info("getResp:"+getResp);
        long endGet = System.currentTimeMillis();
        if(getResp.indexOf("0.0") >= 0){
            httpGetCount++;
            httpGetUsedTime = httpGetUsedTime+(endGet-startGet);
            logger.info("/httpget succ count:"+httpGetCount+"|,average:"+httpGetUsedTime/httpGetCount+"ms,fail:"+httpGetFailCount);
        }else {
            httpGetFailCount++;
        }

        String body = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.card.list\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"type\":\"\",\"searchText\":\"\",\"page\":1,\"pagesize\":5}";
        long startPost = System.currentTimeMillis();
        try{
            String postResp = HttpRequestUtil.postRequeset(BASE_URL_LOCAL+"/xicatcard/api",body);
//            logger.info("postResp:"+postResp);
            long endPost = System.currentTimeMillis();
            if(postResp.indexOf("\"result_code\":\"0\"") >= 0){
                httpPostCount++;
                httpPostUsedTime = httpPostUsedTime+(endPost-startPost);
                logger.info("/httpPost succ count:"+httpPostCount+"|,average:"+httpPostUsedTime/httpPostCount+"ms,fail:"+httpPostFailCount);
            }else {
                httpPostFailCount++;
            }
        }catch (Exception e){
            httpPostFailCount++;

        }
    }


}
