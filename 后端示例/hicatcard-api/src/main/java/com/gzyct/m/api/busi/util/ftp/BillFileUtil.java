//package com.gzyct.m.api.busi.util.ftp;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.List;
//
//import com.gzyct.m.api.busi.db.entity.bill.TBillFtpConf;
//
//
//
///**
// * 本地文件相關
// * 
// * @author Administrator
// *
// */
//public class BillFileUtil {
//	
//	// 处理结果
//	public static String HANDLE_RESULT_SUCCESS = "0"; // 成功
//	
//	/**
//	 * 根据filename 和 内容来保存文件
//	 * 
//	 * @param fileName
//	 * @param content
//	 * @throws Exception
//	 */
//	public static void saveLocalFile(String fileName, String content) throws Exception {
//
//		try {
//			File targetFile = new File(fileName);
//			if (!targetFile.exists()) {
//				targetFile.createNewFile();
//			}
//
//			BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
//			// 换行
//			bw.write(content);
//			bw.close();
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//	
//	/**
//	 * 上传文件到ftp
//	 * 
//	 * @param yctConf
//	 * @param fileName
//	 * @param fileContent
//	 * @param whetherOverRide
//	 *            是否覆盖文件
//	 * @return
//	 * @throws Exception
//	 */
//	public static String toUploadFileByFtp(TBillFtpConf billFtpConf, String fileName, String fileContent,
//			boolean whetherOverRide) throws Exception {
//		String resultString = "";
//		try {
//
//			FtpUtil ftpUtil = new FtpUtil();
//			ftpUtil.connect(billFtpConf.getFtpServer(), Integer.parseInt("" + billFtpConf.getFtpPort()), billFtpConf.getFtpUser(),
//					billFtpConf.getFtpPass(), true);
//			List<String> files = ftpUtil.list(billFtpConf.getFtpDir());
//			if (whetherOverRide == false) {
//				for (String file : files) {
//					if (file.equals(fileName)) {
//						resultString = "文件已存在, 请确认. " + fileName;
//						//logger.info(resultString);
//						return resultString;
//					}
//				}
//			}
//			if (!ftpUtil.setWorkingDirectory(billFtpConf.getFtpDir())) {
//				ftpUtil.disconnect();
//				resultString = "不能进入目录: " + billFtpConf.getFtpDir();
//				//logger.error(resultString);
//				return resultString;
//			}
//			ftpUtil.upload(fileName, fileContent);
//			ftpUtil.disconnect();
//			return HANDLE_RESULT_SUCCESS;
//		} catch (Exception e) {
//			//logger.error(e.getMessage());
//			resultString = "FTP上传错误";
//			return resultString;
//		}
//	}
//}
