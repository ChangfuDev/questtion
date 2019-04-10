package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.result.LayUiResultData;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.RoleService;
import cn.edu.dlnu.question.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @ApiOperation(value = "获取用户信息", notes = "通过用户id获取用户信息")
  @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "int")
  @GetMapping("/get/{id}")
  public User getById(@PathVariable("id") Integer id) {
    return userService.getById(id);
  }

  @ApiOperation(value = "登录", notes = "通过用的username和password进行登录")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "username", value = "用户的username", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "password", value = "用户的password", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "rememberMe", value = "是否记录该用户", required = true, dataType = "boolean")
  })
  @PostMapping(value = "/login")
  public String login(@RequestParam("username") String username,
      @RequestParam("password") String password, @RequestParam("rememberMe") Boolean rememberMe) {
    System.out.println(username);
    UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
    try {
      SecurityUtils.getSubject().login(token);
    } catch (Exception e) {
      return "fail";
    }
    return "success";
  }

  @ApiOperation(value = "添加用户", notes = "通过User对象进行添加")
  @ApiImplicitParam(paramType = "body", name = "user", value = "User对象", required = true, dataType = "user")
  @PostMapping("/add")
  public boolean add(@RequestParam("username") String username,@RequestParam("pass")String password,@RequestParam("r_id")String[] r_id) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    int i = 0;
    try {
      i = userService.add(user,r_id);
    } catch (Exception e) {
      return false;
    }

    return i > 0;
  }

  @ApiOperation(value = "修改密码", notes = "通过帐号密码修改密码")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "username", value = "用户帐号", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "password", value = "原密码", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "repassword", value = "新密码", required = true, dataType = "string")
  })
  @PostMapping("/update")
  public boolean update(@RequestParam("username") String username,
      @RequestParam("password") String password, @RequestParam("repassword") String repassword) {
    User byName = userService.findByName(username);
    if (password.equals(byName.getPassword())) {
      byName.setPassword(repassword);
      int update = userService.update(byName);
      return update > 0 ? true : false;
    }
    return false;
  }

  @ApiOperation(value = "获取管理员信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/admin/list")
  public LayUiResultDataList listAdmin(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    if (page > 0 && limit > 0) {
      return userService.list(page, limit, "admin");
    }
    return LayUiResultDataList.error();
  }

  @ApiOperation(value = "获取普通用户信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/list")
  public LayUiResultDataList list(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    if (page > 0 && limit > 0) {
      return userService.list(page, limit, "customer");
    }
    return LayUiResultDataList.error();
  }
  @ApiOperation(value = "删除", notes = "通过学生id删除")
  @ApiImplicitParam(paramType = "path", name = "ids", value = "学生id", required = true, dataType = "int",allowMultiple = true)
  @PostMapping("/delete/{ids}")
  public LayUiResultData delete(@PathVariable("ids") Integer... ids) {
    if (ids == null || ids.length < 1) {
      return LayUiResultData.error();
    }
    boolean delete = userService.delete(ids);
    if (delete) {
      return LayUiResultData.ok("删除成功");
    } else {
      return LayUiResultData.fail("删除失败");
    }
  }
}
