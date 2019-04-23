package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.GradeLine;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.vo.GradeLineVO;
import java.util.List;

public interface GradeLineService {


  List<GradeLineVO> getByCity(Integer id);
}
