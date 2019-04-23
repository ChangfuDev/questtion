package cn.edu.dlnu.question.dao;

import cn.edu.dlnu.question.entity.GradeLine;
import cn.edu.dlnu.question.entity.GradeLineExample;
import cn.edu.dlnu.question.vo.GradeLineVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeLineMapper {
    int countByExample(GradeLineExample example);

    int deleteByExample(GradeLineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GradeLine record);

    int insertSelective(GradeLine record);

    List<GradeLine> selectByExample(GradeLineExample example);

    GradeLine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GradeLine record, @Param("example") GradeLineExample example);

    int updateByExample(@Param("record") GradeLine record, @Param("example") GradeLineExample example);

    int updateByPrimaryKeySelective(GradeLine record);

    int updateByPrimaryKey(GradeLine record);

  List<GradeLineVO> getByCity(Integer id);
}