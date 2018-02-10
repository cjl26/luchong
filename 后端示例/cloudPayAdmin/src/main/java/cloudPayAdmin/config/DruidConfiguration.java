package cloudPayAdmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class DruidConfiguration {

    @Autowired
    Environment env;

    private final Logger logger = Logger.getLogger(getClass());

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
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
            logger.error("数据库配置获取失败"+e.getMessage());
            return null;
        }
        return datasource;
    }

}