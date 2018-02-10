package com.gzyct.m.api.busi.util;

import java.security.MessageDigest;
import java.util.*;

public class WxPayUtil {

    /**
     * 计算MD5值
     *
     * @param origin      字符串
     * @param charsetname 字符编码
     * @return String MD5值
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 计算随机值
     *
     * @return String nonce_str
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

    /**
     * 获取时间戳 自1970.1.1以来的秒数
     *
     * @return 时间戳
     */
    public static String getCurrentTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 计算WEIXIN参数签名值
     *
     * @param signParams   参数列表
     * @param paramSignKey 签名密钥
     * @param charsetname  字符编码
     * @return 签名值
     */
    public static String genWxPaySign(SortedMap<String, String> signParams, String paramSignKey, String charsetname) {
        StringBuffer sb = new StringBuffer();
        Set es = signParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + paramSignKey);
        String sign = MD5Encode(sb.toString(), charsetname).toUpperCase();
        System.out.println("test---------------------");
        System.out.println("WX SIGN STR = "+sb.toString());
        System.out.println("WX SIGN key = "+paramSignKey);
        System.out.println("WX SIGN sign = "+sign);

        //String signTest = MD5Encode("appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA&key=192006250b4c09247ec02edce69f6a2d", charsetname).toUpperCase();
        //System.out.println("signTest = " + signTest);
        return sign;
    }
}
