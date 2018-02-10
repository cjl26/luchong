package cloudPayAdmin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;

import java.net.ConnectException;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

public class HttpRequestUtil {


	protected final static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	private static CloseableHttpClient httpClient = null;
	static {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000).setSocketTimeout(15000)
				.build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	}
	
	/**
	 * 发出POST请求
	 * @param url 地址
	 * @param content 内容
	 * @return 应答
	 */
	public static String postRequeset(String url, String content){
		logger.info("url : "+url);
		logger.info("req : "+content);
		CloseableHttpResponse resp = null;
		try{
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(content,"UTF-8");
			entity.setChunked(true);
			post.setEntity(entity);

			resp = httpClient.execute(post);
			if(resp!=null){
				HttpEntity ent = resp.getEntity();
				if(ent!=null){
					String result = EntityUtils.toString(ent);
					logger.info("resp : "+result);
					return result;
				}
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
		CloseableHttpResponse resp = null;
		try{

			HttpGet request  = new HttpGet(url);

			resp = httpClient.execute(request );
			if(resp!=null){
				HttpEntity ent = resp.getEntity();
				if(ent!=null)return EntityUtils.toString(ent);
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

	/**
	 * 发出GET请求
	 * @param url 地址
	 * @return 应答
	 */
	public static String getRequestProxy(String url,String proxyIp,int proxyPort){
		CloseableHttpResponse resp = null;
		try{
//			HttpHost target = new HttpHost("localhost", 443, "https");
			HttpHost proxy = new HttpHost(proxyIp, proxyPort, "http");

			RequestConfig config = RequestConfig.custom()
					.setProxy(proxy)
					.build();

			HttpGet request  = new HttpGet(url);
			request.setConfig(config);

			resp = httpClient.execute(request );
			if(resp!=null){
				HttpEntity ent = resp.getEntity();
				if(ent!=null)return EntityUtils.toString(ent);
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
     * 判断是不是静态资源请求
     * @param request
     * @return
     */
    public static boolean isStaticResouceRequest(HttpServletRequest request) {
    	String uri = request.getRequestURI();
    	if(StringUtils.indexOfAny(uri, new String[]{"/adminlte","/css","/exceltemplate","/img","/js","/imgValidate",".ico"}) == -1) {
			return false;
		} else {
			return true;
		}	
    }

}