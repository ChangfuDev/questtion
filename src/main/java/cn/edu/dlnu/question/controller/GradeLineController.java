package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.City;
import cn.edu.dlnu.question.entity.GradeLine;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.GradeLineService;
import cn.edu.dlnu.question.vo.GradeLineVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gradeline")
public class GradeLineController {

  @Autowired
  private GradeLineService gradeLineService;

  @Autowired
  private CityController cityController;


  @GetMapping("/getByCity")
  public List<GradeLineVO> getByCity(@RequestParam("city") String name,
      @RequestParam("type") String type) {
    City city = cityController.getCity(name, type);
    if (city != null) {
      return gradeLineService.getByCity(city.getId());

    }
    return null;

  }

}
