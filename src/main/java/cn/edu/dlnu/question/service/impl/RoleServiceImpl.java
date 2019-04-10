package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.RoleMapper;
import cn.edu.dlnu.question.entity.Role;
import cn.edu.dlnu.question.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleMapper roleMapper;

  @Override
  public boolean add(Role role) {
    return roleMapper.insertSelective(role) > 0;
  }
}
