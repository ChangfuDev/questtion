package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @ApiOperation(value = "添加",notes = "通过Role对象进行添加")
  @ApiImplicitParam(paramType = "query",name = "role",value = "添加的role对象",required = true,dataType = "role")
  @PostMapping("/add")
  public boolean add(@RequestParam("role")Role role){
    return roleService.add(role);
  }

}
