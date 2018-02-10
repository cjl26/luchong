package com.gzyct.m.api.busi.util.annotation;

import java.lang.annotation.*;

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
