package cloudPayAdmin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cloudPayAdmin.util.annotation.ExcelColumn;
import cloudPayAdmin.util.annotation.ExcelIndex;


/**
 * poi excel工具类
 * 
 * @author hyj
 *
 */
public class POIExcelUtils {

	/**
	 * 多个对应关系之间的分隔符
	 */
	public static final String MATCH_SEPERATOR = "|";

	/**
	 * 单个对应关系之间的分隔符
	 */
	public static final String MATCH_SUB_SEPERATOR = ":";

	public static String getCellValue(Cell cell) {
		Object result = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				result = String.format("%.2f", result);  //先保留两位小数，如果是两位小数是00，后面会用正则判断并且去掉
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				result = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_ERROR:
				result = cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			default:
				break;
			}
		}
		return StringUtils.trim(result.toString());
	}
	
	
	/**
	 * 
	 * excel转为对象,要求，对象的属性类型只能为
	 * String,Integer,Long,Short,BigDecimal,Date,Double,Float,属性要有@excelcolumn注解
	 * ，日期类型的属性需要使用dateFormat属性指定excel对应的日期格式
	 * 
	 * @param file
	 * @param clazz
	 * @throws FileNotFoundException
	 */
	public static <T> List<T> excelToObject(File file, Class<T> clazz, int sheetAt) {
		List<T> objs = new ArrayList<T>();
		if (file != null) {
		
			Workbook wb = createWorkbook(file);
			
			Sheet sheet = wb.getSheetAt(sheetAt);
			Row row = sheet.getRow(0);

			Map<Integer, String> header = new HashMap<Integer, String>();
			for (int i = 0; i < row.getLastCellNum(); i++) {
				header.put(i, getCellValue(row.getCell(i)));
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row dataRow = sheet.getRow(i);
				if(dataRow != null) {
					T obj = null;
					try {
						obj = clazz.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				
					for (int j = 0; j < dataRow.getLastCellNum(); j++) {
						String headerValue = StringUtils.trim(header.get(j));
						String value = getCellValue(dataRow.getCell(j));
						Field[] fields = clazz.getDeclaredFields();
						for (Field field : fields) {
							ExcelColumn ec = field.getAnnotation(ExcelColumn.class);
							if (ec != null) {
								String dateFormat = ec.dateFormat();
								if (StringUtils.trim(ec.value()).equals(StringUtils.trim(headerValue))) {
									// excel和数据库对应关系的转换 例如 是 转换为1 否转换为 0 之类的
									String match = ec.match();
									if (StringUtils.isNotBlank(match)) {
										transferMatchValue(value, match);
									}

									setBeanValue( obj, value, field, dateFormat);   //为bean设置值
								}
							}

						}

					}

					objs.add(obj);
				}				
			}
		}
		return objs;
	}
	
	/**
	 * 
	 * excel转为对象,要求，对象的属性类型只能为
	 * String,Integer,Long,Short,BigDecimal,Date,Double,Float,属性要有@excelcolumn注解
	 * ，日期类型的属性需要使用dateFormat属性指定excel对应的日期格式
	 * 
	 * @param file
	 * @param clazz
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public static <T> List<T> excelToObject(MultipartFile multiFile, Class<T> clazz, int sheetAt) {
		List<T> objs = new ArrayList<T>();
		if (multiFile != null) {
		
			Workbook wb = createWorkbook(multiFile);
			
			Sheet sheet = wb.getSheetAt(sheetAt);
			Row row = sheet.getRow(0);

			Map<Integer, String> header = new HashMap<Integer, String>();
			for (int i = 0; i < row.getLastCellNum(); i++) {
				header.put(i, getCellValue(row.getCell(i)));
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row dataRow = sheet.getRow(i);
				if(dataRow != null) {
					T obj = null;
					try {
						obj = clazz.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				
					for (int j = 0; j < dataRow.getLastCellNum(); j++) {
						String headerValue = StringUtils.trim(header.get(j));
						String value = getCellValue(dataRow.getCell(j));
						Field[] fields = clazz.getDeclaredFields();
						for (Field field : fields) {
							ExcelColumn ec = field.getAnnotation(ExcelColumn.class);
							if (ec != null) {
								String dateFormat = ec.dateFormat();
								if (StringUtils.trim(ec.value()).equals(StringUtils.trim(headerValue))) {
									// excel和数据库对应关系的转换 例如 是 转换为1 否转换为 0 之类的
									String match = ec.match();
									if (StringUtils.isNotBlank(match)) {
										transferMatchValue(value, match);
									}

									setBeanValue( obj, value, field, dateFormat);   //为bean设置值
								}
							}

						}

					}

					objs.add(obj);
				}				
			}
		}
		return objs;
	}

	
	/**
	 * 根据excel的列的下标进行转换
	 * @param file
	 * @param clazz
	 * @param sheetAt
	 * @return
	 */
	public static <T> List<T> excelToObjectByIndex(File file, Class<T> clazz, int sheetAt) {
		List<T> objs = new ArrayList<T>();
		if (file != null) {
			Workbook wb = createWorkbook(file);
			
			Sheet sheet = wb.getSheetAt(sheetAt);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row dataRow = sheet.getRow(i);
				if(dataRow != null) {
					T obj = null;
					try {
						obj = clazz.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}			
					for (int j = 0; j < dataRow.getLastCellNum(); j++) {
						String value = getCellValue(dataRow.getCell(j));
						Field[] fields = clazz.getDeclaredFields();
						for (Field field : fields) {
							ExcelIndex ec = field.getAnnotation(ExcelIndex.class);
							if (ec != null) {
								String dateFormat = ec.dateFormat();    //对应excel的日期格式
								if (ec.value() == j) {
									// excel和数据库对应关系的转换 例如 是 转换为1 否转换为 0 之类的
									String match = ec.match();
									if (StringUtils.isNotBlank(match)) {
										transferMatchValue(value,match);   //转换excelMatch表达式
									}								
									setBeanValue( obj, value, field, dateFormat);   //为bean设置值						
								}
							}
						}
					}
					
					objs.add(obj);
				}
				
			}
		
		}
		
		return objs;
	}
	


	
	/**
	 * 对象转为excel
	 * 
	 * @param objs
	 * @param clazz
	 * @param fileName
	 */
	public static <T> File objectToExcel(List<T> objs, Class<T> clazz, String fileName) {

		try {
			Workbook wb = null;
			File file = new File(fileName);  
			if (!file.exists()) {
				file.createNewFile();
			}
			String extendName = FilenameUtils.getExtension(fileName);
			if (StringUtils.equals(extendName, "xls")) {
				wb = new HSSFWorkbook();
			} else if (StringUtils.equals(extendName, "xlsx")) {
				wb = new XSSFWorkbook();
			}

			Sheet sheet = wb.createSheet("sheet1");
			Row firstRow = sheet.createRow(0);
			Field[] fields = clazz.getDeclaredFields();
			// 写表头
			int h = 0;
			for (int i = 0; i < fields.length; i++) {								
				Field field = fields[i];
				String value = "";
				if(field.getAnnotation(ExcelColumn.class) != null) {
					value = field.getAnnotation(ExcelColumn.class).value();
					Cell cell = firstRow.createCell(h);
					cell.setCellValue(value);
					h++;
				}								
			}

			// 写表格数据
			for (int i = 0; i < objs.size(); i++) {
				T obj = objs.get(i);
				Row row = sheet.createRow(i + 1);
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					ExcelColumn ec = fields[j].getAnnotation(ExcelColumn.class);
					if (ec != null) {
						String fieldName = field.getName();
						String columnName = ec.value();
						String dateFormat = ec.dateFormat();
						String match = ec.match();
						for (int k = 0; k < firstRow.getLastCellNum(); k++) {
							String headerValue = getCellValue(firstRow.getCell(k));
							if (StringUtils.equals(StringUtils.trim(headerValue), StringUtils.trim(columnName))) {
								Cell cell = row.createCell(k);
								String value = BeanUtils.getProperty(obj, fieldName);
								
								//转换日期格式
								if (StringUtils.isNotBlank(dateFormat)) {
									try {
										value = new SimpleDateFormat(dateFormat).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(value));
									} catch (Exception e) {
										//这个是oracle出来的日期格式
										value = new SimpleDateFormat(dateFormat).format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US).parse(value));
									}
									
								}
								
								//转换match表达式
								if (StringUtils.isNotBlank(match)) {
									String[] matchs = StringUtils.splitByWholeSeparator(match, MATCH_SEPERATOR);
									for (String mch : matchs) {
										// 属性的值
										String fValue = StringUtils.substringBefore(mch, MATCH_SUB_SEPERATOR);

										// excel 里面的值
										String eValue = StringUtils.substringAfter(mch, MATCH_SUB_SEPERATOR);

										if (StringUtils.equals(value, fValue)) {
											value = eValue;
										}
									}
								}
								
								cell.setCellValue(value);
							}
						}
					}
				}
			}

			// 调整宽度自适应
			for (int i = 0; i < firstRow.getLastCellNum(); i++) {			
				sheet.setColumnWidth(i, 3000); 		
			}
			wb.write(new FileOutputStream(file));
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 为bean设置值
	 * @param obj
	 * @param value
	 * @return
	 */
	private static <T> T setBeanValue(T obj,String value,Field field,String dateFormat) {
		try {
			Class claz = field.getType();
			if (claz == String.class) {
				String regEx = "[0-9]{1,}\\.[0]{1,}";
				boolean rs = RegexUtil.match(value, regEx);
				if(rs) {
					value = StringUtils.substringBefore(value, "."); // 去掉小数点
				}						
				BeanUtils.setProperty(obj, field.getName(), value);
			} else if (claz == Long.class || claz == long.class) {
				if(StringUtils.isNotBlank(value)){
					value = StringUtils.substringBefore(value, "."); // 去掉小数点
					BeanUtils.setProperty(obj, field.getName(), Long.parseLong(value));
				}						
			} else if (claz == Integer.class || claz == int.class) {
				if(StringUtils.isNotBlank(value)) {
					value = StringUtils.substringBefore(value, "."); // 去掉小数点										
					BeanUtils.setProperty(obj, field.getName(), Integer.parseInt(value));
				}						
			} else if (claz == Double.class || claz == double.class) {
				if(StringUtils.isNotBlank(value)) {
					BeanUtils.setProperty(obj, field.getName(), Double.parseDouble(value));
				}
			} else if (claz == Short.class || claz == short.class) {
				if(StringUtils.isNotBlank(value)) {
					value = StringUtils.substringBefore(value, "."); // 去掉小数点
					BeanUtils.setProperty(obj, field.getName(), Short.parseShort(value));
				}
			} else if (claz == Float.class || claz == float.class) {
				if(StringUtils.isNotBlank(value)) {
					BeanUtils.setProperty(obj, field.getName(), Float.parseFloat(value));
				}										
			} else if (claz == BigDecimal.class) {
				BeanUtils.setProperty(obj, field.getName(), new BigDecimal(value));
			} else if (claz == Date.class) {
				if (StringUtils.isNotBlank(dateFormat)) {
					SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
					Date date = null;
					try {
						if (StringUtils.equals("yyyyMMddHHmmss", dateFormat)) {
							value = StringUtils.replaceEachRepeatedly(value, new String[]{"-",":","."," ","/"},new String[]{"","","","",""});
						}												
						date = sdf.parse(value);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					BeanUtils.setProperty(obj, field.getName(), date);
				}										
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 转换excelMatch表达式
	 * @param value
	 * @param match
	 * @return
	 */
	private static String transferMatchValue(String value,String match) {
		
		String[] matchs = StringUtils.splitByWholeSeparator(match, MATCH_SEPERATOR);
		for (String mch : matchs) {
			// 属性的值
			String fValue = StringUtils.substringBefore(mch, MATCH_SUB_SEPERATOR);

			// excel 里面的值
			String eValue = StringUtils.substringAfter(mch, MATCH_SUB_SEPERATOR);

			if (StringUtils.equals(value, eValue)) {
				value = fValue;
			}
		}
		
		return value;
	}
	
	/**
	 * 根据excel文档构建workbook
	 * @param file
	 * @return
	 */
	private static Workbook  createWorkbook(File file) {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			try {
				wb = new XSSFWorkbook(new FileInputStream(file));
			} catch (FileNotFoundException e1) {				
				e1.printStackTrace();
				throw new RuntimeException();
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		return wb;
	}
	
	/**
	 * 根据excel文档构建workbook
	 * @param file
	 * @return
	 */
	private static Workbook  createWorkbook(MultipartFile multiFile) {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(multiFile.getInputStream());
		} catch (Exception e) {
			try {
				wb = new XSSFWorkbook(multiFile.getInputStream());
			} catch (FileNotFoundException e1) {				
				e1.printStackTrace();
				throw new RuntimeException();
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		return wb;
	}
}
