package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.CityMapper;
import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.CityExample;
import cn.edu.dlnu.question.entity.User;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {
  @Autowired
  private CityMapper cityMapper;

  @Override
  public List<City> getAllByYear(Integer year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year,0,0);
    CityExample cityExample = new CityExample();
    cityExample.createCriteria().andUpdatedGreaterThan(calendar.getTime());
    List<City> cities = cityMapper.selectByExample(cityExample);
    return cities;
  }

  @Override
  public boolean add(City city) {
    return cityMapper.insertSelective(city) > 0;
  }

  @Override
  public List<City> findAll() {
    CityExample cityExample = new CityExample();
    return cityMapper.selectByExample(cityExample);
  }

  @Override
  public City getById(Integer city_id) {
    return cityMapper.selectByPrimaryKey(city_id);
  }

  @Override
  public LayUiResultDataList list(Integer page, Integer limit) {
    PageHelper.startPage(page, limit);
    List<City> cities =  cityMapper.selectByExample(new CityExample());
    if (cities == null) {
      return LayUiResultDataList.error();
    }
    PageInfo pageInfo = new PageInfo(cities);
    return LayUiResultDataList.ok(cities, pageInfo.getTotal());
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public boolean delete(Integer[] ids) {
    for (Integer id : ids) {
      if (cityMapper.deleteByPrimaryKey(id) < 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean update(City city) {
    return cityMapper.updateByPrimaryKeySelective(city) > 0;
  }
}