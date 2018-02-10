package cloudPayAdmin.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对象，列明映射注解
 * @author hyj
 *
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ExcelColumn {
	String value() default "";
	String dateFormat() default "";
	//example : 1:是|0:否   冒号左边为属性值，右边为excel的值
	String match() default "";
}
