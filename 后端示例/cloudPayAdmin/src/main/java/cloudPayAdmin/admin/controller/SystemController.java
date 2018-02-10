package cloudPayAdmin.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cloudPayAdmin.constant.Constant;
import cloudPayAdmin.util.HttpUtil;

@Controller
@RequestMapping("/cloudpay/system")
public class SystemController {
	
	@Autowired
	Environment env;
	
	/**
	 * 跳转到错误页
	 * @return
	 */
	@RequestMapping("/toErrorPage")
	public String toError(){
		return "common/error";
	}
	
	/**
	 * 跳转到未授权页
	 * @return
	 */
	@RequestMapping("/toUnAuthorizationPage")
	public String toUnAuthorization() {
		return "common/unAuthorization";
	}
	
	@RequestMapping("/exportExcelData")
	public String exportExcelData(HttpServletRequest request) throws FileNotFoundException, IOException {
		String[] headers = StringUtils.splitByWholeSeparator(request.getParameter("header"), "|");
		String[] values = StringUtils.splitByWholeSeparatorPreserveAllTokens(request.getParameter("excelData"), "|");
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		Row firstRow = sheet.createRow(0);
		
		//写表头
		for(int i=0;i<headers.length;i++) {
			Cell cell =	firstRow.createCell(i, Cell.CELL_TYPE_STRING);
			cell.setCellValue(headers[i]);
		}
		
		//写表格数据
		int rowIndex = 1;
		Row row  = null;
		for(int j=0;j<values.length;j++) {
			//Row row = sheet.createRow(rowIndex);
			if(j % headers.length == 0) {
				row = sheet.createRow(rowIndex);
				rowIndex++;
			}
			
			Cell cell = row.createCell(j % headers.length, Cell.CELL_TYPE_STRING);
			//把html换行符转成window的换行符
			String cellValue = values[j];
			int index = StringUtils.lastIndexOfAny(cellValue, new String[]{"<br/>","<br>","</br>"});
			if(index >= 0) {
				cellValue = StringUtils.substring(cellValue, 0, index);
			}
			cell.setCellValue(StringUtils.replaceEach(cellValue, new String[]{"<br/>","<br>","</br>"}, new String[]{"\r\n","\r\n","\r\n"}));			
		}
		
		String fileName = Constant.EXCEL_FILE_PATH + File.separator + System.currentTimeMillis() + ".xlsx";
		String filePath = env.getProperty("image.upload.base.path") + fileName;
		
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		wb.write(new FileOutputStream(file));
		
		String redirectUrl = env.getProperty("image.http.base.path") + env.getProperty("image.upload.database.suffix.path") + StringUtils.replace(fileName, File.separator, HttpUtil.HTTP_PATH_SEPERATOR); 
		return "redirect:" + redirectUrl;
	}
}
