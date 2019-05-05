package cn.edu.dlnu.question.service.impl;

import cn.edu.dlnu.question.dao.CityMapper;
import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.CityExample;
import cn.edu.dlnu.question.entity.CityExample.Criteria;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.CityService;
import cn.edu.dlnu.question.vo.CityVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    List<City> cities = cityMapper.getAllByYear(year);
    return cities;
  }

  @Override
  public boolean add(City city) {
    try{
      return cityMapper.insertSelective(city) > 0;
    }catch (Exception e){
      e.printStackTrace();
    }
    return false;
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

  @Override
  public City getCity(String name, String type) {
    CityExample cityExample = new CityExample();
    Criteria criteria = cityExample.createCriteria();
    criteria.andNameEqualTo(name);
    criteria.andTypeEqualTo(type);
    List<City> cities = cityMapper.selectByExample(cityExample);
    if(cities != null && cities.size() > 0){
      return cities.get(0);
    }
    return null;
  }

  @Override
  public List<CityVO> getCities() {
    return cityMapper.getCities();
  }
}
