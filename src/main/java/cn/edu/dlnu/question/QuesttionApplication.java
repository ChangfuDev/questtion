package cn.edu.dlnu.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages  = {"cn.edu.dlnu.question.dao"})
@EnableSwagger2
public class QuesttionApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuesttionApplication.class, args);
  }

}
