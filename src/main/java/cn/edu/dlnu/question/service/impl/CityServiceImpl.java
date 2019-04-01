package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.CityMapper;
import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.CityExample;
import cn.edu.dlnu.question.service.CityService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return cityMapper.insert(city) > 0;
  }

  @Override
  public List<City> findAll() {
    CityExample cityExample = new CityExample();
    return cityMapper.selectByExample(cityExample);
  }
}
