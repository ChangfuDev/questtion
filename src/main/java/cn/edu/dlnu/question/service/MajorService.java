package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.Major;
import cn.edu.dlnu.question.result.LayUiResultDataList;

public interface MajorService {

  int add(Major major);

  LayUiResultDataList list(Integer page, Integer limit);

  boolean delete(Integer[] ids);

  int update(Major major);

  Major getById(Integer id);
}
