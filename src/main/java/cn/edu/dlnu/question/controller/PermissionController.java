package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.service.PermissionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("permission")
public class PermissionController {

  @Autowired
  private PermissionService permissionService;

  @ApiOperation(value = "添加",notes = "通过Permission对象进行添加")
  @ApiImplicitParam(paramType = "query",name = "permission",value = "Permission对象",required = true,dataType = "permission")
  @PostMapping("/add")
  public boolean add(@RequestParam("permission")Permission permission){
    return permissionService.add(permission);
  }

  @ApiOperation(value = "修改权限",notes = "通过id修改")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query",name = "id",value = "权限对应的id",required = true,dataType = "int"),
      @ApiImplicitParam(paramType = "query",name = "name",value = "权限对应的name",required = true,dataType = "string")
  })
  @PostMapping("/update")
  public boolean update(@RequestParam("id")Integer id,@RequestParam("name")String name){
    Permission permission = new Permission();
    permission.setId(id);;
    permission.setName(name);
    return permissionService.update(permission);
  }

  @ApiOperation(value = "findAll",notes = "获得所有的权限")
  @GetMapping("/findAll")
  public List<Permission> getAll(){
    return permissionService.findAll();
  }

}
