package cloudPayAdmin.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ExcelIndex {
	int value() default 0;
	String dateFormat() default "yyyyMMddHHmmss";
	//example : 1:是|0:否   冒号左边为属性值，右边为excel的值
	String match() default "";
}
