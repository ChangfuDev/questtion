package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.GradeLineMapper;
import cn.edu.dlnu.question.service.GradeLineService;
import cn.edu.dlnu.question.vo.GradeLineVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeLineServiceImpl implements GradeLineService {

  @Autowired
  private GradeLineMapper gradeLineMapper;

  @Override
  public List<GradeLineVO> getByCity(Integer id) {

    return gradeLineMapper.getByCity(id);
  }
}
