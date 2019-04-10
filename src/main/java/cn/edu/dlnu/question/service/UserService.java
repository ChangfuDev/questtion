package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.result.LayUiResultDataList;

public interface UserService {

  User getById(Integer id);

  User findByName(String name);

  int add(User user,String[] r_id) throws Exception;

  int update(User byName);

  LayUiResultDataList list(Integer page, Integer limit, String admin);

  boolean delete(Integer[] ids);
}
