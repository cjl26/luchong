package com.gzyct.m.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.project.m.api.common.util.SpringUtils;

@SpringBootApplication(scanBasePackages = { "com.gzyct.m.api", "com.project.m.api" })
@EnableScheduling
@EnableCaching
@EnableAsync
//@EnableDiscoveryClient
//@ImportResource({"classpath:disconf.xml"})//引入disconf
public class ApplicationMain extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationMain.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
		
		//设置微信小程序配置信息
		setWxaConfig();
	}
	
	public static void setWxaConfig() {
		WxaConfig wxaConfig = new WxaConfig();
		wxaConfig.setAppId(SpringUtils.getProperty("weixin.miniapp.appid"));
		wxaConfig.setAppSecret(SpringUtils.getProperty("weixin.miniapp.secret"));
		WxaConfigKit.setWxaConfig(wxaConfig);

	}
}
