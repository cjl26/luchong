package com.gzyct.m.api.busi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import org.springframework.core.env.Environment;
/**
 * modify at 2017/6/12.
 */
@Configuration
public class DruidConfiguration {

    @Autowired
    Environment env;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 注册ServletRegistrationBean
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        /** 初始化参数配置，initParams**/
        //白名单
//        bean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//        bean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", env.getProperty("spring.application.name"));
        //是否能够重置数据.
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * 注册FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return bean;
    }

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
        	datasource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
            datasource.setUrl(env.getProperty("spring.datasource.url"));
            datasource.setUsername(env.getProperty("spring.datasource.username"));
            datasource.setPassword(env.getProperty("spring.datasource.password"));

            datasource.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initialSize")));
            datasource.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.minIdle")));
            datasource.setMaxWait(Long.valueOf(env.getProperty("spring.datasource.maxWait")));
            datasource.setMaxActive(Integer.valueOf(env.getProperty("spring.datasource.maxActive")));
            datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
            datasource.setMinEvictableIdleTimeMillis(Long.valueOf(env.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
            //设置连接最大生存时间，解决mysql 8小时回收连接，但是连接池还是有效连接导致报错的问题
            datasource.setMaxEvictableIdleTimeMillis(Long.valueOf(env.getProperty("spring.datasource.maxEvictableIdleTimeMillis")));
            datasource.setValidationQuery(env.getProperty("spring.datasource.validationQuery"));
            datasource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("spring.datasource.testWhileIdle")));
            datasource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnBorrow")));
            datasource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnReturn")));
            datasource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.datasource.poolPreparedStatements")));
            datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
            datasource.setFilters(env.getProperty("spring.datasource.filters"));
        }catch (Exception e){
            logger.error("数据库配置获取失败",e);
            return null;
        }
        return datasource;
    }

}