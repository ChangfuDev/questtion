package cn.edu.dlnu.question;

import cn.edu.dlnu.question.dao.UserMapper;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuesttionApplicationTests {
  @Autowired
  private UserService userService;
  @Autowired
  private UserMapper userMapper;
  @Test
  public void contextLoads() {
    User user = userMapper.selectByPrimaryKey(1);
    System.out.println(user);
  }

  @Test
  public void testService(){
    User byId = userService.getById(1);
    System.out.println(byId);

  }
  @Test
  public void testFindByName(){
    User byId = userService.findByName("2015136327");
    System.out.println(byId);

  }


}
