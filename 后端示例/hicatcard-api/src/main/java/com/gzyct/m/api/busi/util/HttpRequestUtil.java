package com.gzyct.m.api.busi.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyStore;

public class HttpRequestUtil {

	protected final static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	private static CloseableHttpClient httpClient = null;
	static {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(20000)
				.build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	}
	
	/**
	 * 支持POST HTTP
	 * 支持 HTTPS
	 * @param url
	 * @param method
	 * @param content
	 * @return
	 */
	public static String generalRequest(String url, String method, String content){
		if(url!=null && url.toLowerCase().startsWith("https")){
			return httpsRequest(url, method, content);
		}
		if(method!=null && method.toLowerCase().equals("post")){
			return postRequeset(url, content);
		}
		return "";
	}
	
	/**
	 * 发出POST请求
	 * @param url 地址
	 * @param content 内容
	 * @return 应答
	 */
	public static String postRequeset(String url, String content){
		logger.info("url:"+url);
		logger.info("req:"+content);
		if(url == null || url.length() == 0 || content == null || content.length() == 0){
			return null;
		}
		CloseableHttpResponse resp = null;
		try{
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(content,"UTF-8");
			entity.setChunked(true);
			post.setEntity(entity);

			resp = httpClient.execute(post);
			if(resp!=null){
				HttpEntity ent = resp.getEntity();
				logger.info("ent.getContent() = " + ent.getContent());
				//InputStream instreams = ent.getContent();


				String respStr = EntityUtils.toString(ent);
				logger.info("res:"+respStr+"\nfor request url:"+url+"\n"+"body:"+content);
				if(respStr!=null)return respStr;
				logger.error("post response is empty");
			}
		}catch(Exception e){
			logger.error("post exception: " + e.getMessage());
		}finally{
			if(resp!=null){
				try{
					resp.close();
				}catch(Exception ex){}
			}
		}
		return "";
	}

	/**
	 * 发出GET请求
	 * @param url 地址
	 * @return 应答
	 */
	public static String getRequest(String url){
		logger.info("url:"+url);
		CloseableHttpResponse resp = null;
		try{
			HttpGet post = new HttpGet(url);
			resp = httpClient.execute(post);
			if(resp!=null){
				HttpEntity ent = resp.getEntity();
				if(ent!=null){
					String respStr = EntityUtils.toString(ent);
					logger.info("res:"+respStr+"\nfor request url:"+url);
					return respStr;
				}
				logger.error("get response is empty");
			}
		}catch(Exception e){
			logger.error("get exception: " + e.getMessage());
		}finally{
			if(resp!=null){
				try{
					resp.close();
				}catch(Exception ex){}
			}
		}
		return "";
	}

//	public static String postBmobRequeset(String url, String content){
//		CloseableHttpResponse resp = null;
//		try{
//			HttpPost post = new HttpPost(url);
//			post.setHeader("X-Bmob-Application-Id","cc4946c276ef110982456d48c877015b");
//			post.setHeader("X-Bmob-REST-API-Key","3928d62db862ba4daf45b33b2629f6fa");
//			post.setHeader("Content-Type","application/json");
//			StringEntity entity = new StringEntity(content,"UTF-8");
////			entity.setChunked(true);
//			entity.setContentType("text/json");
//			post.setEntity(entity);
//
//			resp = httpClient.execute(post);
//			if(resp!=null){
//				HttpEntity ent = resp.getEntity();
//				if(ent!=null)return EntityUtils.toString(ent);
//				logger.error("post response is empty");
//			}
//		}catch(Exception e){
//			logger.error("post exception: " + e.getMessage());
//		}finally{
//			if(resp!=null){
//				try{
//					resp.close();
//				}catch(Exception ex){}
//			}
//		}
//		return "";
//	}
	/**
     * @return
     */
    private static CloseableHttpClient createSSLClientDefault(Environment env, String certPrefix, String certPath) {
    	CloseableHttpClient httpClientBuilder=null;
    	SSLConnectionSocketFactory sslsf = null;
    	try {
    		//using cert
    		String FILE_SUFFIX = ".pfx";
    		String filePath = certPath + certPrefix + FILE_SUFFIX;
    		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
    		FileInputStream instream = new FileInputStream(new File(filePath));
    		try {
    			//设置客户端证书
    			keyStore.load(instream, certPrefix.toCharArray());
    		} catch (Exception e) {
    			logger.error("导入证书错误" + e);
    		} finally {
    			if (instream != null) {
    				instream.close();
    			}
    		}
    		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certPrefix.toCharArray()).build();
    		sslsf = new SSLConnectionSocketFactory(sslcontext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

    		//using proxy
    		if(env.getProperty("yct.proxy.proxyOn")!=null && env.getProperty("yct.proxy.proxyOn").equalsIgnoreCase("true")){
    			HttpHost proxy = new HttpHost(env.getProperty("yct.proxy.server.https.ip"),Integer.parseInt(env.getProperty("yct.proxy.server.https.port")));
        		httpClientBuilder= HttpClients.custom().setSSLSocketFactory(sslsf).setProxy(proxy).build();
    		}else{
        		httpClientBuilder= HttpClients.custom().setSSLSocketFactory(sslsf).build();
    		}
            
            return httpClientBuilder;
        } catch (Exception e) {
            logger.error("创建https导入证书错误"+e);
        }
        return HttpClients.createDefault();
    }

 
    public static String httpsCertRequest(Environment env, String requestUrl, String certPrefix, String certPath, String requestMethod, String outputStr) {
    	String result = "";
    	CloseableHttpClient localClient = null;
    	CloseableHttpResponse response = null;
    	try {
    		localClient= HttpRequestUtil.createSSLClientDefault(env, certPrefix, certPath);
    		
    		HttpPost httppost = new HttpPost(requestUrl);
    		StringEntity se=new StringEntity(outputStr, "UTF-8");
    		httppost.setEntity(se);
    		response = localClient.execute(httppost);
    		
    		HttpEntity entity = response.getEntity();
    		if (entity != null) {
    			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
    			String text;
    			while ((text = bufferedReader.readLine()) != null) {
    				result += text;
    			}
    		}
    		return result;
    	}catch(Exception ex){
    		logger.error("https request error:{}"+requestUrl, ex);
    	}finally {
    		try{
    			if(response!=null)response.close();
    			if(localClient!=null)localClient.close();
    		}catch(Exception e){}
    	}
    	return "";
   }
	/**
     * 发起https请求
     *
     * @param requestUrl 请求地址
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static String httpsCertRequest(String requestUrl, String certPrefix, String certPath, String requestMethod, String outputStr) {
       try {
    	   String result = "";

    	   String FILE_SUFFIX = ".pfx";
    	   String filePath = certPath + certPrefix + FILE_SUFFIX;

    	   KeyStore keyStore  = KeyStore.getInstance("PKCS12");
           FileInputStream instream = new FileInputStream(new File(filePath));
           try {
               keyStore.load(instream, certPrefix.toCharArray());
           } finally {
               instream.close();
           }

           // Trust own CA and all self-signed certs
           SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certPrefix.toCharArray()).build();
           // Allow TLSv1 protocol only
           SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                   sslcontext,
                   new String[] { "TLSv1" },
                   null,
                   SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

           CloseableHttpClient httpclient = HttpClients.custom()
                   .setSSLSocketFactory(sslsf)
                   .build();
           try {
               HttpPost httppost = new HttpPost(requestUrl);
               StringEntity se=new StringEntity(outputStr, "UTF-8");
               httppost.setEntity(se);
               CloseableHttpResponse response = httpclient.execute(httppost);
               try {
                   HttpEntity entity = response.getEntity();
                   if (entity != null) {
                       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                       String text;
                       while ((text = bufferedReader.readLine()) != null) {
                    	   result += text;
                       }
                   }
               } finally {
                   if(response!=null)response.close();
               }
           } finally {
               if(httpclient!=null)httpclient.close();
           }
           return result;
       } catch (ConnectException ce) {
    	   logger.error("Weixin server connection timed out.");
       } catch (Exception e) {
    	   logger.error("https request error:{}"+requestUrl, e);
       }
       return "";
   }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回值
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            boolean bfirstline = true;
            while ((str = bufferedReader.readLine()) != null) {
                if(bfirstline)
                {
                    buffer.append(str);
                    bfirstline = false;
                }
                else
                {
                    buffer.append("\n" + str);
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            String response = buffer.toString();
            response = new String(response.getBytes(), "utf-8");
//           String encoding = getEncoding(response);
//           LOGGER.info("The encoding of the string is " + encoding);
//           String utf8Sting = new String(response.getBytes(encoding),"UTF-8");
//           String encoding2 = YCTCommonUtil.getEncoding(utf8Sting);
//           LOGGER.info("The encoding of the utf8Sting is " + encoding2);
            return response;
        } catch (ConnectException ce) {
            logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}"+requestUrl, e);
        }
        return "";
    }
}