package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.Permission;
import java.util.List;

public interface PermissionService {

  boolean add(Permission permission);

  boolean update(Permission permission);

  List<Permission> findAll();
}
