package cloudPayAdmin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author hyj
 *
 */
public class RegexUtil {
	
	public static boolean match(String source,String regex) {
		
		Pattern pattern = Pattern.compile(regex);										
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}
}
