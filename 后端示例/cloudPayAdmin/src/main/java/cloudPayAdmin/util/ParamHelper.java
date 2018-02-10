package cloudPayAdmin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamHelper {
	
	protected final static Logger logger = LoggerFactory.getLogger(ParamHelper.class);
	
	/**
	 * 获取一个对象的所有字段及其值，放到map对象中
	 * @param obj
	 * @param params
	 * @return
	 */
	public static boolean getFiledValues(Object obj, SortedMap<String, String> params){
		if(obj==null)return false;
		Class claz = obj.getClass();
		while(!claz.getName().endsWith(".Object")){
			Field [] fields = claz.getDeclaredFields();
			for(Field fd : fields){
				try{
					getFieldValues(fd, claz, obj, params);
				}catch(Exception ex){
				}
			}
			
			claz = claz.getSuperclass();
		}
		
		return true;
	}
	
	private static void getFieldValues(Field fd, Class claz, Object obj, SortedMap<String, String> params) throws Exception {
		String fdName = fd.getName();
		String firstLetter = fdName.substring(0,1).toUpperCase();
		String getMethodName = "get" + firstLetter + fdName.substring(1);
		Method getMethod = claz.getMethod(getMethodName, new Class[]{});
		Object value = getMethod.invoke(obj,new Object[]{});
		params.put(fdName, value.toString());
	}
	
	/**
	 * 计算参数签名值
	 * @param signParams    参数列表
	 * @param paramSignKey    签名密钥
	 * @param charsetname    字符编码
	 * @return  签名值
	 */
	public static String genMd5Sign(SortedMap<String, String> signParams, String paramSignKey, String charsetname)
	{
		StringBuffer sb = new StringBuffer();
		Set es = signParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)	&& !"key".equals(k))
			{
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + paramSignKey);
		String toSignString = sb.toString();
		String sign = MD5Util.MD5Encode(toSignString, charsetname).toUpperCase();
		logger.info("toSignString: " + toSignString);
		logger.info("sign output: " + sign);
		return sign;
	}
	
	/**
	 * 计算参数签名值
	 * @param obj    对象
	 * @param paramSignKey    签名密钥
	 * @param charsetname    字符编码
	 * @return  签名值
	 */
	public static String genMd5Sign(Object obj, String paramSignKey, String charset){
		SortedMap<String, String> params = new TreeMap<String, String>();
		if(ParamHelper.getFiledValues(obj, params)){
			String sign = genMd5Sign(params, paramSignKey, charset);
			return sign;
		}
		return "";
	}
	
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
}
