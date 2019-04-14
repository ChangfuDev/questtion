package cn.edu.dlnu.question;

import cn.edu.dlnu.question.dao.UserMapper;
import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.entity.Student;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.service.CityService;
import cn.edu.dlnu.question.service.PermissionService;
import cn.edu.dlnu.question.service.StudentService;
import cn.edu.dlnu.question.service.UserService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class QuesttionApplicationTests {

  @Autowired
  private UserService userService;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private CityService cityService;

  @Autowired
  private PermissionService permissionService;

  @Autowired
  private StudentService studentService;

  @Test
  public void contextLoads() {
    User user = userMapper.selectByPrimaryKey(1);
    System.out.println(user);
  }

  @Test
  public void testService() {
    User byId = userService.getById(1);
    System.out.println(byId);

  }

  @Test
  public void testFindByName() {
    User byId = userService.findByName("2015136327");
    System.out.println(byId);

  }

  @Test
  public void testGetAllByYear() {
    List<City> allByYear = cityService.getAllByYear(2019);
    System.out.println(allByYear.toString());

  }
  @Test
  public void testGetAll() {
    List<Permission> all = permissionService.findAll();
    System.out.println(all.toString());

  }
  @Test
  public void testFindAll() {
    List<Student> all = studentService.findAll();
    System.out.println(all.toString());

  }

}
