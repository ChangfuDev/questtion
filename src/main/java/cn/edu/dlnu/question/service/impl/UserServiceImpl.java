package cn.edu.dlnu.question.service.impl;

import static sun.plugin2.os.windows.OSVERSIONINFOA.size;

import cn.edu.dlnu.question.dao.PermissionMapper;
import cn.edu.dlnu.question.dao.UserMapper;
import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.entity.PermissionExample;
import cn.edu.dlnu.question.entity.PermissionExample.Criteria;
import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PermissionMapper permissionMapper;

  @Override
  public User getById(Integer id) {
    System.out.println(id);
    return userMapper.selectByPrimaryKey(1);
  }

  @Override
  public User findByName(String name) {
    User user = userMapper.findByName(name);
    if(user != null && user.getRoles() !=null && user.getRoles().size() > 0){

      List<Role> roles = user.getRoles();
      for (int i = 0; i < roles.size(); i++) {
        PermissionExample example = new PermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andRIdEqualTo(roles.get(i).getId());
        List<Permission> permissions = permissionMapper.selectByExample(example);
        roles.get(i).setPermissions(permissions);
      }
    }
    return user;
  }
}
