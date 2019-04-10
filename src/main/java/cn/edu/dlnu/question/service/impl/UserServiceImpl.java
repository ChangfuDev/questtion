package cn.edu.dlnu.question.service.impl;

import static sun.plugin2.os.windows.OSVERSIONINFOA.size;

import cn.edu.dlnu.question.dao.PermissionMapper;
import cn.edu.dlnu.question.dao.RoleMapper;
import cn.edu.dlnu.question.dao.UserMapper;
import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.entity.PermissionExample;
import cn.edu.dlnu.question.entity.PermissionExample.Criteria;
import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.entity.RoleExample;
import cn.edu.dlnu.question.entity.Student;
import cn.edu.dlnu.question.entity.StudentExample;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PermissionMapper permissionMapper;
  @Autowired
  private RoleMapper roleMapper;

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

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public int add(User user,String[] r_id) throws Exception {
    if(userMapper.insertSelective(user) > 0){
      for (String s : r_id) {
        int id =user.getId();
        Role role = new Role();
        role.setName(s);
        role.setuId(id);
        int i = roleMapper.insertSelective(role);
        if(i < 1){
          throw new Exception();
        }
      }
      return user.getId();
    }
    return 0;
  }

  @Override
  public int update(User byName) {
    int i = userMapper.updateByPrimaryKeySelective(byName);
    return i;
  }

  @Override
  public LayUiResultDataList list(Integer page, Integer limit, String admin) {
    PageHelper.startPage(page, limit);
    List<User> students =  userMapper.list(admin);
    if (students == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(students);
    return LayUiResultDataList.ok(students, pageInfo.getTotal());
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean delete(Integer[] ids) {
    for (Integer id : ids) {
      RoleExample example = new RoleExample();
      example.createCriteria().andUIdEqualTo(id);
      if (roleMapper.deleteByExample(example) < 0 &&  userMapper.deleteByPrimaryKey(id) < 1) {
        return false;
      }
    }
    return true;
  }
}
