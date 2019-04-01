package cn.edu.dlnu.question.service;

import cn.edu.dlnu.question.entity.City;
import java.util.List;

public interface CityService {

  List<City> getAllByYear(Integer year);

  boolean add(City city);

  List<City> findAll();
}
