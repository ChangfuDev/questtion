package cn.edu.dlnu.question.controller;


import cn.edu.dlnu.question.entity.Major;
import cn.edu.dlnu.question.result.LayUiResultData;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.MajorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/major")
public class MajorController {

  @Autowired
  private MajorService majorService;

  @ApiOperation(value = "添加专业信息", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "content", value = "专业介绍", required = true, dataType = "String")
  })
  @PostMapping("/add")
  public int add(@RequestParam("name") String name, @RequestParam("content") String content) {
    Major major = new Major();
    major.setName(name);
    major.setContent(content);
    return majorService.add(major);
  }

  @ApiOperation(value = "获取普通用户信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/list")
  public LayUiResultDataList list(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    if (page > 0 && limit > 0) {
      return majorService.list(page, limit);
    }
    return LayUiResultDataList.error();
  }


  @ApiOperation(value = "删除", notes = "通过学生id删除")
  @ApiImplicitParam(paramType = "path", name = "ids", value = "学生id", required = true, dataType = "int", allowMultiple = true)
  @PostMapping("/delete/{ids}")
  public LayUiResultData delete(@PathVariable("ids") Integer... ids) {
    if (ids == null || ids.length < 1) {
      return LayUiResultData.error();
    }
    boolean delete = majorService.delete(ids);
    if (delete) {
      return LayUiResultData.ok("删除成功");
    } else {
      return LayUiResultData.fail("删除失败");
    }
  }


  @ApiOperation(value = "更新", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "id", value = "名称", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "content", value = "专业介绍", required = true, dataType = "String")
  })
  @PostMapping("/update")
  public int update(@RequestParam("id") Integer id,@RequestParam("name") String name, @RequestParam("content") String content) {
    Major major = new Major();
    major.setId(id);
    major.setName(name);
    major.setContent(content);
    return majorService.update(major);
  }
  @ApiOperation(value = "获取所有的city", notes = "获取所有的city通过年份")
  @ApiImplicitParam(paramType = "path", name = "id", value = "city的id", required = true, dataType = "int")
  @GetMapping("/get/{id}")
  public Major getById(@PathVariable("id") Integer id) {
    return majorService.getById(id);
  }

}
