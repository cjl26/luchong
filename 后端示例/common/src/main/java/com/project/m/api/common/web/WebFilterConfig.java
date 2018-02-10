package com.project.m.api.common.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfig {

	@Bean
	public FilterRegistrationBean interfaceContextWebFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new InterfaceContextWebFilter());
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}

}
