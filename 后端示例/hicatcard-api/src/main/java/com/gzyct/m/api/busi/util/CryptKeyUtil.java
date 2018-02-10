package com.gzyct.m.api.busi.util;

public class CryptKeyUtil {

	/**
	 * 分散因子=UIID（3字节）+用户ID后5字节
	 * 分散密钥=3des(分散因子+NOT（分散因子）)=3des(key,1234564455667788EDCBA9BBAA998877)
	 * @param userId
	 * @return
	 */
	public static String genSubKey(String uuid, String skey, String userId){
		
		byte []factor = new byte[8];
		copyNumberStrProp(uuid, 3, factor, 0);
		copyNumberStrProp(userId, 5, factor, 3);
		
		byte []source = new byte[16];
		for(int i=0; i<8; i++){
			source[i] = factor[i];
			source[i+8] = (byte)(0xFF - factor[i]);
		}
		
		//String tsource = CommonConvertor.byteToHex(source);
		//logger.info("try to encrypt: " + tsource);
		byte[] keyByte18 = CommonConvertor.hexStringToBytes(skey);
		byte[] keyByte24 = new byte[24];
		System.arraycopy(keyByte18, 0, keyByte24, 0, 16);
		System.arraycopy(keyByte18, 0, keyByte24, 16, 8);
		
		byte subkeyByte[] = ThreeDES.encryptMode(keyByte24, source);
		String subkey = CommonConvertor.byteToHex(subkeyByte);
		subkey = subkey.substring(0, source.length*2);
		return subkey;
	}
	
	public static int copyNumberStrProp(String prop, int lenPop, byte []data, int startPos){
		while(prop.length()<lenPop*2){
			prop = "0" + prop;
		}
		if(prop.length()>lenPop*2)prop = prop.substring(prop.length()-lenPop*2);
		byte []byteProp = str2Bcd(prop);
		System.arraycopy(byteProp, 0, data, startPos, byteProp.length);
		startPos = startPos + byteProp.length;
		return startPos;
	}
	
	/** 
     * @功能: 10进制串转为BCD码 
     * @参数: 10进制串 
     * @结果: BCD码 
     */  
    public static byte[] str2Bcd(String asc) {  
        int len = asc.length();  
        int mod = len % 2;  
        if (mod != 0) {  
            asc = "0" + asc;  
            len = asc.length();  
        }  
        byte abt[] = new byte[len];  
        if (len >= 2) {  
            len = len / 2;  
        }  
        byte bbt[] = new byte[len];  
        abt = asc.getBytes();  
        int j, k;  
        for (int p = 0; p < asc.length() / 2; p++) {  
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
                j = abt[2 * p] - '0';  
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
                j = abt[2 * p] - 'a' + 0x0a;  
            } else {  
                j = abt[2 * p] - 'A' + 0x0a;  
            }  
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
                k = abt[2 * p + 1] - '0';  
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
                k = abt[2 * p + 1] - 'a' + 0x0a;  
            } else {  
                k = abt[2 * p + 1] - 'A' + 0x0a;  
            }  
            int a = (j << 4) + k;  
            byte b = (byte) a;  
            bbt[p] = b;  
        }  
        return bbt;  
    }
}
