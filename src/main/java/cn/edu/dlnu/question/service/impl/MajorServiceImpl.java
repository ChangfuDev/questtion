package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.MajorMapper;
import cn.edu.dlnu.question.entity.Major;
import cn.edu.dlnu.question.entity.MajorExample;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.MajorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MajorServiceImpl implements MajorService {

  @Autowired
  private MajorMapper majorMapper;

  @Override
  public int add(Major major) {
    try {
      return majorMapper.insertSelective(major);
    }catch (Exception e){
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public LayUiResultDataList list(Integer page, Integer limit) {
    PageHelper.startPage(page, limit);
    List<Major> majors = majorMapper.selectByExample(new MajorExample());
    if (majors == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(majors);
    return LayUiResultDataList.ok(majors, pageInfo.getTotal());
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
  public boolean delete(Integer[] ids) {
    for (Integer id : ids) {
      int i = majorMapper.deleteByPrimaryKey(id);
      if (i < 1) {
        throw new RuntimeException("专业删除失败");
      }
    }
    return true;
  }

  @Override
  public int update(Major major) {
    return majorMapper.updateByPrimaryKeySelective(major);
  }

  @Override
  public Major getById(Integer id) {
    return majorMapper.selectByPrimaryKey(id);
  }
}
