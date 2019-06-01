package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.MessageMapper;
import cn.edu.dlnu.question.entity.Message;
import cn.edu.dlnu.question.entity.MessageExample;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageMapper messageMapper;

  @Override
  public LayUiResultDataList list(Integer page, Integer limit) {
    PageHelper.startPage(page, limit);
    List<Message> messages = messageMapper.selectByExampleWithBLOBs(new MessageExample());
    if (messages == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(messages);
    return LayUiResultDataList.ok(messages, pageInfo.getTotal());
  }

  @Override
  public boolean add(Message message) {
    boolean flag = false;
    try {
      flag = messageMapper.insertSelective(message) > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  @Override
  public Message getMessage(Integer id) {
    return messageMapper.selectByPrimaryKey(id);
  }

  @Override
  public boolean update(Message message) {
    return messageMapper.updateByPrimaryKeySelective(message) > 0;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
  public boolean delete(Integer[] ids) {
    for (Integer id : ids) {
      if(messageMapper.deleteByPrimaryKey(id) < 1){
        throw new RuntimeException("删除失败");
      }

    }
    return true;
  }

  @Override
  public LayUiResultDataList wx() {
    return LayUiResultDataList.ok(messageMapper.selectByExample(new MessageExample()));
  }
}
