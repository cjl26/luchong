package com.gzyct.m.api.busi.util.rsa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.gzyct.m.api.busi.bean.card.CardBindListResp;
import com.project.m.api.common.biz.resp.BizResp;

import java.util.Map;


public class RSATester {
	public static void main2(String args[]){

		try {
			Map<String,Object> keyMap = RSAUtil.initKey();
			String pk = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAM6UHIgmAk8BGBL1+X0fj2hW7p70CtxkyhOU9aS3fCwZ63awtTJ2E3fJs2/rEfbSrgSb2EcK2LryFiIfFEkFGWECAwEAAQ==";//RSAUtil.getPublicKey(keyMap);
			String prk = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAzpQciCYCTwEYEvX5fR+PaFbunvQK3GTKE5T1pLd8LBnrdrC1MnYTd8mzb+sR9tKuBJvYRwrYuvIWIh8USQUZYQIDAQABAkBqc9CwKEYQSFQLkU3buKPB9OpKZyAhqp6nsJ9VHmiSoGqZyExX4C1QsSIHfPvcaOqfoSSv3tvaF+hzNnVx7+kBAiEA/bsk7B71R5MiF46LZEhfSfjz0+t20U7yYYFz3rLga6kCIQDQbQXm+KpEOwVHtGKIlpzRC5jx8flF2HVz9aqwWmaS+QIgIQZErf7xidlarJo/BlRtIqGmisSw+FlALvS8chXyI9ECIDXjyn/i0Ztcq0vT7xoj3x4J4pzEFNCjXBMEdQKuDRF5AiAoNPsBO0gHCpgJ+eowdmqhFhQLt4/mgbs80YLM2uPMaA==";
			String plain = "c4ca4238a0b923820dcc509a6f75849b";	//md5(1)
			byte[] cipher=RSAUtil.encryptByPublicKey(plain.getBytes(), pk);
			String cipher64 = RSAUtil.encryptBASE64(cipher);

			byte []decrypted = RSAUtil.decryptByPrivateKey(RSAUtil.decryptBASE64(cipher64), prk);
			String decryptedTxt = new String(decrypted);

			System.out.println("pk: " + pk);
			System.out.println("prk: " + prk);
			System.out.println("decryptedTxt: " + decryptedTxt);
			System.out.println("cipher64: " + cipher64);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
