package com.gzyct.m.api.busi.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
		http.csrf().disable();

		setProxy();
	}

	private void setProxy() {
		String sProxyOn = env.getProperty("yct.proxy.proxyOn");
		if (StringUtils.isEmpty(sProxyOn))
			return;
		if (sProxyOn.equals("false"))
			return;

		Properties prop = System.getProperties();
		// 设置http访问要使用的代理服务器的地址,端口
		prop.setProperty("http.proxyHost", env.getProperty("yct.proxy.server.http.ip"));
		prop.setProperty("http.proxyPort", env.getProperty("yct.proxy.server.http.port"));
		// 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
		prop.setProperty("http.nonProxyHosts", "localhost|10.240.*|10.250.*");

		// 设置安全访问使用的代理服务器地址与端口
		// 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
		prop.setProperty("https.proxyHost", env.getProperty("yct.proxy.server.https.ip"));
		prop.setProperty("https.proxyPort", env.getProperty("yct.proxy.server.https.port"));

		// 使用ftp代理服务器的主机、端口以及不需要使用ftp代理服务器的主机
		// prop.setProperty("ftp.proxyHost", "10.10.0.96");
		// prop.setProperty("ftp.proxyPort", "2121");
		prop.setProperty("ftp.nonProxyHosts", "localhost|10.240.*|10.250.*");
	}
}
