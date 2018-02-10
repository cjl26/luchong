//package com.gzyct.m.api.busi.util;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Date;
//import org.springframework.core.env.Environment;
//import com.aliyun.oss.HttpMethod;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.GeneratePresignedUrlRequest;
//
//public class AliyunUtil {
//
//
//	public static String BUCKET_NAME_YCTAPP = "yctapp";
//	   // endpoint以杭州为例，其它region请按实际情况填写
//    public static  String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
//    // accessKey请登录https://ak-console.aliyun.com/#/查看
//    public static  String accessKeyId = "";
//    public static  String accessKeySecret = "";
//
//	private static  OSSClient ossClient  = null;
//
//	static {
//		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//	}
//
//    /**
//     * 上传图片到阿里云OSS
//     * @param env
//     * @param bucketName
//     * @param picPath
//     */
//    public static void uploadFile(Environment env, String bucketName, String picPath ,String filename ) {
//
//        // 上传文件
//        //boolean f = new File(picPath).exists();
//        ossClient.putObject(bucketName, filename, new File(picPath));
//    }
//
//    /**
//     * @param bucketName 存储空间名  "yctapp"
//     * @param picPath    图片路径，包括图片名称  "card/hello.jpg"
//     * @param style      图片展示模式,
//     *                   比如   缩放 image/resize,m_fixed,w_200,h_100
//     * @return
//     * @minutes 过期时间N分钟
//     */
//    public static String getPicUrl(Environment env, String bucketName, String picPath, String style, long minutes) {
//
//
//        if (env != null) {
//            if (env.getProperty("aliyun.endpoint") != null) endpoint = env.getProperty("aliyun.endpoint");
//            if (env.getProperty("aliyun.accessKeyId") != null) accessKeyId = env.getProperty("aliyun.accessKeyId");
//            if (env.getProperty("aliyun.accessKeySecret") != null)
//                accessKeySecret = env.getProperty("aliyun.accessKeySecret");
//        }
//
//        Date expiration = new Date(new Date().getTime() + 1000 * 60 * minutes);
//        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, picPath, HttpMethod.GET);
//        req.setExpiration(expiration);
//        if (style != null && !style.equals("")) req.setProcess(style);
//
//        URL signedUrl = ossClient.generatePresignedUrl(req);
//
//        return signedUrl.toString();
//    }
//
//    /**
//     * 获取公共全下的bucket的url
//     * @param bucketName
//     * @param picPath
//     * @param ssl
//     * @return
//     */
//    public static String getPublicUrl(String bucketName,String picPath,boolean ssl) {
//    	StringBuilder url = new StringBuilder();
//    	if (ssl == Boolean.TRUE) {
//    		url.append("https://");
//    	} else {
//    		url.append("http://");
//    	}
//
//    	url.append(bucketName).append(".").append(endpoint.replace("http://", "")).append("/").append(picPath);
//
//    	return url.toString();
//    }
//
//
//    public static void main2(String args[]) {
//       // String url = getPicUrl(null, "yctapp", "card/TestPicttt.jpg", "image/resize,m_fixed,w_200,h_100", 30);
////        uploadFile(null,"yctappext","D:\\222.jpg","222.jpg");
//    }
//}
