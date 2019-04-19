package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.PermissionMapper;
import cn.edu.dlnu.question.entity.Permission;
import cn.edu.dlnu.question.entity.PermissionExample;
import cn.edu.dlnu.question.service.PermissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  private PermissionMapper permissionMapper;

  @Override
  public boolean add(Permission permission) {
    return permissionMapper.insertSelective(permission) > 0;
  }

  @Override
  public boolean update(Permission permission) {
    return permissionMapper.updateByPrimaryKeySelective(permission) > 0;

  }

  @Override
  public List<Permission> findAll() {
    PermissionExample permissionExample = new PermissionExample();
    return permissionMapper.selectByExample(permissionExample);
  }
}
