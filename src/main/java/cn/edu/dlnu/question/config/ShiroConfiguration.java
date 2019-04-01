package cn.edu.dlnu.question.config;


import cn.edu.dlnu.question.realm.MyRealm;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

/**
 * @author zjh
 */
@Configuration
public class ShiroConfiguration {

  @Bean
  public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
    System.out.println("ShiroConfiguration.shirFilter()");
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
//拦截器.
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    filterChainDefinitionMap.put("/**", "anon");


//// 配置不会被拦截的链接 顺序判断
//    filterChainDefinitionMap.put("/static/**", "anon");
////配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//    filterChainDefinitionMap.put("/logout", "logout");
////<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
////<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//    filterChainDefinitionMap.put("/user/**", "authc");
//// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
// 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");

//未授权界面;
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }

  @Bean
  public MyRealm myShiroRealm() {
    MyRealm myShiroRealm = new MyRealm();
    return myShiroRealm;
  }


  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(myShiroRealm());
    return securityManager;
  }

  /**
   * cookie对象;
   */
  public SimpleCookie rememberMeCookie() {
//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//<!-- 记住我cookie生效时间30天 ,单位秒;-->
    simpleCookie.setMaxAge(2592000);
    return simpleCookie;
  }

  /**
   * cookie管理对象;记住我功能
   */
  public CookieRememberMeManager rememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
    cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
    return cookieRememberMeManager;
  }

}
