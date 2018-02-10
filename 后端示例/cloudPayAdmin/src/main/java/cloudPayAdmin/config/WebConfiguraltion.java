package cloudPayAdmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cloudPayAdmin.inteceptor.LoginSessionInteceptor;

@Configuration
public class WebConfiguraltion extends WebMvcConfigurerAdapter {

	@Bean
	public LoginSessionInteceptor loginSessionInteceptor() {
		return new LoginSessionInteceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
	    registry.addInterceptor(loginSessionInteceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
