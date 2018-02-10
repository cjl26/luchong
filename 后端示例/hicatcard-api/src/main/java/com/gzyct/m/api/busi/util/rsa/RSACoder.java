package com.gzyct.m.api.busi.util.rsa;

import java.security.MessageDigest;

import com.gzyct.m.api.busi.util.Base64;


public class RSACoder {
	public static final String KEY_SHA="SHA";
	public static final String KEY_MD5="MD5";
	
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception{
		//return (new BASE64Decoder()).decodeBuffer(key);
		return Base64.decode(key.getBytes("UTF-8"));
	}
	
	/**
	 * BASE64加密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key)throws Exception{
		//return (new BASE64Encoder()).encodeBuffer(key);
		return new String(Base64.encode(key),"UTF-8");
	}
	
	/**
	 * MD5加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data)throws Exception{
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}
	
	/**
	 * SHA加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data)throws Exception{
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
}
