package cn.edu.dlnu.question.controller;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(maxAge = 3600)
public class LogOutController {
  @RequestMapping(value = "/logout")
  public String logout(ServletRequest request, ServletResponse response){
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "redirect:pages/login.html";
  }

}
