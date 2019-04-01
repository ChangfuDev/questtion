package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.User;

public interface UserService {

  User getById(Integer id);

  User findByName(String name);
}
