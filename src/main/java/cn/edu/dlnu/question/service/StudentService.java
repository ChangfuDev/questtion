package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.Student;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import java.util.List;

public interface StudentService {

  int add(Student student);

  boolean delete(Integer[] id);

  List<Student> findAll();

  LayUiResultDataList list(Integer page, Integer limit);

  LayUiResultDataList listByCity(Integer page, Integer limit, Integer id);

  Student getById(Integer id);

  int update(Student student);

  LayUiResultDataList listByName(Integer page, Integer limit, String name);

  int getByName(String name);
}
