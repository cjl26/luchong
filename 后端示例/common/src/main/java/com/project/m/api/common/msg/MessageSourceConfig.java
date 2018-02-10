package com.project.m.api.common.msg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

//@Component(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
@Component
public class MessageSourceConfig extends ReloadableResourceBundleMessageSource {

	@Value("${yctapi.common.msgSrcCacheSeconds:900}")
	private int msgSrcCacheSeconds;

	public MessageSourceConfig() {
		this.setDefaultEncoding("UTF-8");
		this.setBasenames(new String[] { "classpath:error", "classpath:common-error" });
	}

	@PostConstruct
	private void afterPropertiesSet() {
		logger.debug("set msgSrcCacheSeconds " + msgSrcCacheSeconds);
		this.setCacheSeconds(msgSrcCacheSeconds);
	}

}
