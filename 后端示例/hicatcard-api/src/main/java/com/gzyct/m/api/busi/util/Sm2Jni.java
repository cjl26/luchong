package com.gzyct.m.api.busi.util;

import com.gzyct.m.api.busi.util.tencent.QPayHttpsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sm2Jni {

	private static final Logger logger = LoggerFactory.getLogger(Sm2Jni.class);
	
	static {
		logger.info("static running" + System.getProperty("java.library.path"));
		try{
			System.loadLibrary("Sm2Jni");
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
	}
	
	public static void testSign(String hexData, String hexPri, String hexSign){
		if(sign(hexData, hexPri, hexSign)){
			System.out.println("sign ok: " + hexSign);
		}else{
			System.out.println("sign failed");
		}
	}
	
	public static boolean sign(String hexData, String hexPri, String hexSign){
		logger.info("entering sign");
		try{
			int dataLen = 32;
			byte []data = CommonConvertor.hexStringToBytes(hexData);
			byte []pri = CommonConvertor.hexStringToBytes(hexPri);
			byte []sign = CommonConvertor.hexStringToBytes(hexSign);
			
			Sm2Jni sj = new Sm2Jni();
			int ret = sj.SM2_Sign(data, dataLen, pri, sign);
			if(ret!=0)return false;
			hexSign = CommonConvertor.byteToHex(sign);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		logger.info("leaving sign");
		return true;
	}
	
	public static void testVerify(String hexData, String hexSign, String hexPub){
		
		if(verify(hexData, hexSign, hexPub)){
			System.out.println("verify ok");
		}else{
			System.out.println("verify failed");
		}
	}

	public static boolean verify(String hexData, String hexSign, String hexPub){
		
		byte []data = CommonConvertor.hexStringToBytes(hexData);
		byte []pub = CommonConvertor.hexStringToBytes(hexPub);
		byte []sign = CommonConvertor.hexStringToBytes(hexSign);
		int dataLen = data.length;
		
		Sm2Jni sj = new Sm2Jni();
		int ret = sj.SM2_Verify(data, dataLen, sign, pub);
		if(ret!=0)return false;
		return true;
	}

	public static void main2(String []args){
//		Sm2Jni sj = new Sm2Jni();
//		sj.hello("hello");
//		
//		int len = 4;
//		byte data[] = new byte[len];
//		data[0] = 'a';
//		data[1] = 'b';
//		data[2] = 'c';
//		data[3] = 'd';
//		System.out.println(data[0]);
//		sj.testUC(data, len);
//		System.out.println(data[0]);
		
		String pub = "785680DE6C4A9E3773D362FD07C00415E0DD15D8C20FC4C4EABF78186AE4C17DD889C02D754B3EE448216C6CE18B875E171A3053C7BE2E0F41B4189047255726";
		String priv = "BB06DD52EE1CAFF62E1554C3FA73C9B59284BA6790DF5603E08A1D36650B7E01";
		String data = "594B54005100010000000000506051201610240000010101C000010101000000";
		String sign = "B4F91F50F4483A5C6DAECCB4177EE8D3CBAE96949E71B3F3FAE4251433687ACFBC3DA849BB1BDF80AEA73ADBCBAA1FE611D16EF1AA26462046EE925319BACC18";
		
		//testVerify(data, sign, pub);
		testSign(data, priv, sign);
		testVerify(data, sign, pub);
		
	}
	
	public native void hello(String name);
	public native void testUC(byte data[], int dataLen);
	public native int SM2_Sign(byte[] data, int dataLen, byte[] pri, byte[] sign);
	public native int SM2_Verify(byte[] data, int dataLen, byte[] signature, byte[] pub);
}
