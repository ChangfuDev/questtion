package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.vo.CityVO;
import java.util.List;

public interface CityService {

  List<City> getAllByYear(Integer year);

  boolean add(City city);

  List<City> findAll();

  City getById(Integer city_id);

  LayUiResultDataList list(Integer page, Integer limit);

  boolean delete(Integer[] ids);

  boolean update(City city);

  City getCity(String name, String type);

  List<CityVO> getCities();
}
