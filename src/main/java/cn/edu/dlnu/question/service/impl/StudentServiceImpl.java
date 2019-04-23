package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.StudentMapper;
import cn.edu.dlnu.question.entity.Student;
import cn.edu.dlnu.question.entity.StudentExample;
import cn.edu.dlnu.question.entity.StudentExample.Criteria;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentMapper studentMapper;

  @Override
  public int add(Student student) {
    if (studentMapper.insertSelective(student) > 0) {
      return student.getId();
    }
    return 0;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean delete(Integer[] ids) {
    for (Integer id : ids) {
      if (studentMapper.deleteByPrimaryKey(id) < 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<Student> findAll() {
    StudentExample studentExample = new StudentExample();
    studentExample.setOrderByClause("total_grade DESC,updated DESC");
    Calendar calendar = Calendar.getInstance();
    calendar.set(calendar.get(Calendar.YEAR), 0, 0);
    Criteria criteria = studentExample.createCriteria();
    criteria.andUpdatedGreaterThan(calendar.getTime());
    return studentMapper.selectByExample(studentExample);
  }

  @Override
  public LayUiResultDataList list(Integer page, Integer limit) {
    PageHelper.startPage(page, limit);
    StudentExample studentExample = new StudentExample();
    studentExample.setOrderByClause("total_grade desc");
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (students == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(students);
    return LayUiResultDataList.ok(students, pageInfo.getTotal());
  }

  @Override
  public LayUiResultDataList listByCity(Integer page, Integer limit, Integer id) {
    PageHelper.startPage(page, limit);
    StudentExample studentExample = new StudentExample();
    studentExample.setOrderByClause("total_grade desc");
    Criteria criteria = studentExample.createCriteria();
    criteria.andCIdEqualTo(id);
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (students == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(students);
    return LayUiResultDataList.ok(students, pageInfo.getTotal());
  }

  @Override
  public LayUiResultDataList listByName(Integer page, Integer limit, String name) {
    PageHelper.startPage(page, limit);
    StudentExample studentExample = new StudentExample();
    studentExample.createCriteria().andNameEqualTo(name);
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (students == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(students);
    return LayUiResultDataList.ok(students, pageInfo.getTotal());
  }

  @Override
  public Student getById(Integer id) {
    return studentMapper.selectByPrimaryKey(id);
  }

  @Override
  public int update(Student student) {
    return studentMapper.updateByPrimaryKeySelective(student);
  }

  @Override
  public int getByName(String name) {
    return studentMapper.getByName(name);
  }
}
