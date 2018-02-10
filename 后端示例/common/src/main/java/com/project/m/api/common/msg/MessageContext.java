package com.project.m.api.common.msg;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class MessageContext {

	private static MessageSourceAccessor messages;

	@Resource(name = "messageSourceConfig", type = MessageSourceConfig.class)
	public void setMessageSource(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	public static MessageSourceAccessor get() {
		return messages;
	}

	public static String getMessage(String code, String defaultMessage) {
		return messages.getMessage(code, defaultMessage);
	}

	public static String getMessage(String code, String defaultMessage, Locale locale) {
		return messages.getMessage(code, defaultMessage, locale);
	}

	public static String getMessage(String code, Object[] args, String defaultMessage) {
		return messages.getMessage(code, args, defaultMessage);
	}

	public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messages.getMessage(code, args, defaultMessage, locale);
	}

	public static String getMessage(String code) throws NoSuchMessageException {
		return messages.getMessage(code);
	}

	public static String getMessage(String code, Locale locale) throws NoSuchMessageException {
		return messages.getMessage(code, locale);
	}

	public static String getMessage(String code, Object[] args) throws NoSuchMessageException {
		return messages.getMessage(code, args);
	}

	public static String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return messages.getMessage(code, args, locale);
	}

}
