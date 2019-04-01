package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "获取用户信息", notes = "通过用户id获取用户信息")
  @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "int")
  @GetMapping("/{id}")
  public User getById(@PathVariable("id") Integer id) {
    System.out.println(id);
    return userService.getById(id);
  }

  @ApiOperation(value = "登录", notes = "通过用的username和password进行登录")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "username", value = "用户的username", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "password", value = "用户的password", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "rememberMe", value = "是否记录该用户", required = true, dataType = "boolean")
  })
  @PostMapping(value = "/login")
  public String login(String username, String password, Boolean rememberMe) {
    System.out.println(username);
    UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
    try{
      SecurityUtils.getSubject().login(token);
    }catch (Exception e){
      return "fail";
    }
    return "success";
  }


}
