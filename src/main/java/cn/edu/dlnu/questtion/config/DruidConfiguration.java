package cn.edu.dlnu.questtion.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public  class DruidConfiguration {
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;
  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;
  @Value("${spring.datasource.initialSize}")
  private int initialSize;
  @Value("${spring.datasource.minIdle}")
  private int minIdle;
  @Value("${spring.datasource.maxActive}")
  private int maxActive;
  @Value("${spring.datasource.maxWait}")
  private int maxWait;
  @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
  private int timeBetweenEvictionRunsMillis;
  @Value("${spring.datasource.minEvictableIdleTimeMillis}")
  private int minEvictableIdleTimeMillis;
  @Value("${spring.datasource.validationQuery}")
  private String validationQuery;
  @Value("${spring.datasource.testWhileIdle}")
  private boolean testWhileIdle;
  @Value("${spring.datasource.testOnBorrow}")
  private boolean testOnBorrow;
  @Value("${spring.datasource.testOnReturn}")
  private boolean testOnReturn;
  @Value("${spring.datasource.poolPreparedStatements}")
  private boolean poolPreparedStatements;
  @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
  private int maxPoolPreparedStatementPerConnectionSize;
  @Value("${spring.datasource.filters}")
  private String filters;
  @Value("{spring.datasource.connectionProperties}")
  private String connectionProperties;

  public DruidConfiguration() {
  }

  @Bean
  @Primary
  public DataSource dataSource() {
    DruidDataSource datasource = new DruidDataSource();
    datasource.setUrl(this.url);
    datasource.setUsername(this.username);
    datasource.setPassword(this.password);
    datasource.setDriverClassName(this.driverClassName);
    datasource.setInitialSize(this.initialSize);
    datasource.setMinIdle(this.minIdle);
    datasource.setMaxActive(this.maxActive);
    datasource.setMaxWait((long)this.maxWait);
    datasource.setTimeBetweenEvictionRunsMillis((long)this.timeBetweenEvictionRunsMillis);
    datasource.setMinEvictableIdleTimeMillis((long)this.minEvictableIdleTimeMillis);
    datasource.setValidationQuery(this.validationQuery);
    datasource.setTestWhileIdle(this.testWhileIdle);
    datasource.setTestOnBorrow(this.testOnBorrow);
    datasource.setTestOnReturn(this.testOnReturn);
    datasource.setPoolPreparedStatements(this.poolPreparedStatements);
    datasource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);

    try {
      datasource.setFilters(this.filters);
    } catch (SQLException var3) {
      var3.printStackTrace();
    }

    datasource.setConnectionProperties(this.connectionProperties);
    return datasource;
  }

  @Bean
  public ServletRegistrationBean statViewServlet() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), new String[]{"/druid/*"});
    servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
    servletRegistrationBean.addInitParameter("deny", "192.168.0.19");
    servletRegistrationBean.addInitParameter("loginUsername", "root");
    servletRegistrationBean.addInitParameter("loginPassword", "root");
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean statFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter(), new ServletRegistrationBean[0]);
    filterRegistrationBean.addUrlPatterns(new String[]{"/*"});
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }
}