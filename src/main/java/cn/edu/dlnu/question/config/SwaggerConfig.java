package cn.edu.dlnu.question.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: lattice
 * @description: ${description}
 * @author: 张家豪
 * @create: 2018-11-14 20:34
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("cn.edu.dlnu.question.controller"))
        .paths(PathSelectors.any()).build();
  }


  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("大连民族大学艺术生咨询相关接口")
        .description("question")
        .termsOfServiceUrl("http://blog.csdn.net/java_yes")
        .version("1.0").build();
  }
}

