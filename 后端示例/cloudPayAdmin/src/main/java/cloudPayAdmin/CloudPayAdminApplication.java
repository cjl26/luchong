package cloudPayAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.project.m.api.common.util.SpringUtils;

@SpringBootApplication(scanBasePackages={"cloudPayAdmin","com.project.m.api"})
@EnableAsync
public class CloudPayAdminApplication {
	
	public static void main(String[] args) {
		ApplicationContext  ctx  = SpringApplication.run(CloudPayAdminApplication.class, args);
		 String[] beanNames =  ctx.getBeanDefinitionNames();

	       /*System.out.println("所以beanNames个数："+beanNames.length);

	       for(String bn:beanNames){

	           System.out.println(bn);

	       }*/
		
		 setWxaApiConfig();
	}
	
	private static void setWxaApiConfig() {
		WxaConfig wxaConfig = new WxaConfig();
		wxaConfig.setAppId(SpringUtils.getProperty("weixin.miniapp.appid"));
		wxaConfig.setAppSecret(SpringUtils.getProperty("weixin.miniapp.secret"));
		WxaConfigKit.setWxaConfig(wxaConfig);
	}
}
