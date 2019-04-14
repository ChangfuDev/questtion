package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.result.LayUiResultData;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
@RequestMapping("/city")
public class CityController {

  @Autowired
  private CityService cityService;

  @ApiOperation(value = "获取所有的city", notes = "获取所有的city通过年份")
  @ApiImplicitParam(paramType = "path", name = "year", value = "获取的年份", required = true, dataType = "int")
  @GetMapping("/year/{year}")
  public List<City> getAllByYear(@PathVariable("year") Integer year) {
    return cityService.getAllByYear(year);
  }

  @ApiOperation(value = "获取所有的city", notes = "获取所有的city通过年份")
  @ApiImplicitParam(paramType = "path", name = "id", value = "city的id", required = true, dataType = "int")
  @GetMapping("/get/{id}")
  public City getById(@PathVariable("id") Integer id) {
    return cityService.getById(id);
  }

  @ApiOperation(value = "获取所有的city", notes = "获取所有的city")
  @GetMapping("/findAll")
  public List<City> findAll() {
    return cityService.findAll();
  }


  @ApiOperation(value = "获取City", notes = "通过name和type获取")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "string")})
  @GetMapping("/getCity")
  public City getCity(@RequestParam("name") String name,@RequestParam("type")String  type) {
    return cityService.getCity(name,type);
  }

  @ApiOperation(value = "获取普通用户信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/list")
  public LayUiResultDataList list(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    if (page > 0 && limit > 0) {
      return cityService.list(page, limit);
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
    boolean delete = cityService.delete(ids);
    if (delete) {
      return LayUiResultData.ok("删除成功");
    } else {
      return LayUiResultData.fail("删除失败");
    }
  }


  @ApiOperation(value = "获取普通用户信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "artisticGrade", value = "艺术成绩系数", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "culturalGrade", value = "文化成绩系数", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "type", value = "分文理0不分 1文科 2理科", required = true, dataType = "String")

  })
  @PostMapping("/add")
  public boolean add( @RequestParam("name") String name,
      @RequestParam("artisticGrade") String artisticGrade,
      @RequestParam("culturalGrade") String culturalGrade,@RequestParam("type")String type) {
    City city = new City();
    city.setArtisticGrade(artisticGrade);
    city.setCulturalGrade(culturalGrade);
    city.setName(name);
    city.setType(type);
    return cityService.add(city);
  }

  @ApiOperation(value = "获取普通用户信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "artisticGrade", value = "艺术成绩系数", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "culturalGrade", value = "文化成绩系数", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "query", name = "type", value = "分文理0不分 1文科 2理科", required = true, dataType = "String")

  })
  @PostMapping("/update")
  public boolean update(@RequestParam("id") String  id, @RequestParam("name") String name,
      @RequestParam("artisticGrade") String artisticGrade,
      @RequestParam("culturalGrade") String culturalGrade,@RequestParam("type")String type) {
    City city = new City();
    city.setId(Integer.parseInt(id));
    city.setArtisticGrade(artisticGrade);
    city.setCulturalGrade(culturalGrade);
    city.setName(name);
    city.setType(type);
    return cityService.update(city);
  }

}
