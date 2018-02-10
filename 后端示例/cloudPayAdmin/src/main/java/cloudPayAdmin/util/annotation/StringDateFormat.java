package cloudPayAdmin.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD,ElementType.METHOD})
public @interface StringDateFormat {
	
	/**
	 * 原本的日期格式
	 * @return
	 */
	String oriFormat() default "";
	
	/**
	 * 要转换的目的日期格式
	 * @return
	 */
	String destFormat() default "";
}
