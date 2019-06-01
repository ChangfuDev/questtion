package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.Student;
import cn.edu.dlnu.question.result.LayUiResultData;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.CityService;
import cn.edu.dlnu.question.service.StudentService;
import cn.edu.dlnu.question.vo.CityVO;
import cn.edu.dlnu.question.vo.CountVo;
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
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;
  @Autowired
  private CityService cityService;

  @ApiOperation(value = "添加", notes = "通过Student对象进行添加")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "username", value = "要添加的Student对象的名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "culturalGrade", value = "要添加的Student对象的文化成绩", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "query", name = "artisticGrade", value = "要添加的Student对象的艺术成绩", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "query", name = "city_id", value = "要添加的Student对象的所在城市", required = true, dataType = "int"),
  })
  @PostMapping("/add")
  public int add(@RequestParam("username") String username,
      @RequestParam("culturalGrade") Integer c_grade,
      @RequestParam("artisticGrade") Integer a_grade,
      @RequestParam("city_id") Integer city_id) {
    City city = cityService.getById(city_id);
    Student student = new Student();
    student.setGrade(c_grade + "#" + a_grade);
    student.setName(username);
    student.setcId(city_id);
    student.setTotalGrade((Integer.valueOf(city.getArtisticGrade()) * a_grade
        + Integer.valueOf(city.getCulturalGrade()) * c_grade) / 100.0);
    return studentService.add(student);
  }

  @ApiOperation(value = "删除", notes = "通过学生id删除")
  @ApiImplicitParam(paramType = "path", name = "ids", value = "学生id", required = true, dataType = "int", allowMultiple = true)
  @PostMapping("/delete/{ids}")
  public LayUiResultData delete(@PathVariable("ids") Integer... ids) {
    if (ids == null || ids.length < 1) {
      return LayUiResultData.error();
    }
    boolean delete = studentService.delete(ids);
    if (delete) {
      return LayUiResultData.ok("删除成功");
    } else {
      return LayUiResultData.fail("删除失败");
    }
  }

  @ApiOperation(value = "查找", notes = "查找所有的Student")
  @GetMapping("/findAll")
  public List<Student> findAll() {
    return studentService.findAll();
  }

  @ApiOperation(value = "获取学生信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/list")
  public LayUiResultDataList list(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    if (page > 0 && limit > 0) {
      return studentService.list(page, limit);
    }
    return LayUiResultDataList.error();
  }

  @ApiOperation(value = "获取学生信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "id", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/listByCity/{id}")
  public LayUiResultDataList listByCity(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit, @PathVariable("id") Integer id) {
    if (page > 0 && limit > 0) {
      return studentService.listByCity(page, limit, id);
    }
    return LayUiResultDataList.error();
  }

  @ApiOperation(value = "获取学生信息列表", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "body", name = "name", value = "当前页数", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "body", name = "page", value = "当前页数", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "body", name = "limit", value = "当前页的数据条数", required = true, dataType = "int")})
  @GetMapping("/listByName/{id}")
  public LayUiResultDataList listByName(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit, @PathVariable("name") String  name) {
    if (page > 0 && limit > 0) {
      return studentService.listByName(page, limit, name);
    }
    return LayUiResultDataList.error();
  }


  @ApiOperation(value = "获取所有的city", notes = "获取所有的city通过年份")
  @ApiImplicitParam(paramType = "path", name = "id", value = "city的id", required = true, dataType = "int")
  @GetMapping("/get/{id}")
  public Student getById(@PathVariable("id") Integer id) {
    return studentService.getById(id);
  }

  @ApiOperation(value = "更新", notes = "通过当前页（page）和每页数据条数（limit）获取该页数据")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "id", value = "名称", required = true, dataType = "int"),
      @ApiImplicitParam(paramType = "query", name = "name", value = "名称", required = true, dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "content", value = "专业介绍", required = true, dataType = "String")
  })
  @PostMapping("/update")
  public int update(@RequestParam("id") Integer id, @RequestParam("name") String name,
      @RequestParam("artisticGrade") Integer artisticGrade,
      @RequestParam("culturalGrade") Integer culturalGrade, @RequestParam("c_id") Integer city_id) {
    Student student = new Student();
    student.setId(id);
    student.setName(name);
    student.setGrade(artisticGrade + "#" + culturalGrade);
    City city = cityService.getById(city_id);
    student.setTotalGrade((Integer.valueOf(city.getArtisticGrade()) * artisticGrade
        + Integer.valueOf(city.getCulturalGrade()) * culturalGrade) / 100.0);
    return studentService.update(student);
  }

  @GetMapping("/getByName")
  public String getByName(@RequestParam("name")String name){
    return studentService.getByName(name)+"";
  }

  @GetMapping("/count")
  public List<CountVo> count(){
  return studentService.count();
  }

}
