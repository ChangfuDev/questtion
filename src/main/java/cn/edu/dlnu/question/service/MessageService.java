package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.Message;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import java.util.List;

public interface MessageService {



  boolean add(Message message);

  Message getMessage(Integer id);

  boolean update(Message message);

  boolean delete(Integer[] id);

  LayUiResultDataList list(Integer page, Integer limit);

  LayUiResultDataList wx();
}
