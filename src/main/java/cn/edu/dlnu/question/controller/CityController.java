package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
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
  @GetMapping("/{year}")
  public List<City> getAllByYear(@PathVariable("year") Integer year) {
    return cityService.getAllByYear(year);
  }

  @ApiOperation(value = "获取所有的city", notes = "获取所有的city")
  @GetMapping("/findAll")
  public List<City> findAll() {
    return cityService.findAll();
  }

  @ApiOperation(value = "添加City", notes = "通过City对象进行添加")
  @ApiImplicitParam(paramType = "query", name = "city", value = "City对象", required = true, dataType = "city")
  @PostMapping("/add")
  public boolean add(@RequestParam("city") City city) {
    return cityService.add(city);
  }
}
