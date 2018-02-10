package com.project.m.api.common.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyConfig {

	private static final Logger logger = LoggerFactory.getLogger(PropertyConfig.class);

	private static PropertiesConfiguration propConfig = null;

	private static String propertyPath = "common.properties";

	static {

		propConfig = new PropertiesConfiguration();
		propConfig.setReloadingStrategy(getFileChangedReloadingStrategy());
		propConfig.setDelimiterParsingDisabled(true);
		propConfig.setEncoding("UTF-8");

		URL url = Thread.currentThread().getContextClassLoader().getResource(propertyPath);

		try {
			propConfig.load(url);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.error("failed to load properties config {} ", propertyPath, e);
		}

	}

	private static FileChangedReloadingStrategy getFileChangedReloadingStrategy() {
		FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
		reloadingStrategy.setRefreshDelay(30 * 60 * 1000);// 30min
		return reloadingStrategy;
	}

	public static String getProp(String propName) {
		return propConfig.getString(propName);
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		ConfigurationUtils.dump(propConfig, pw);
		pw.close();
		return sw.toString();
	}
}
