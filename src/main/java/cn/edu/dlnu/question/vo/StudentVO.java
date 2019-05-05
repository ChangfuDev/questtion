package cn.edu.dlnu.question.vo;

import java.util.Date;
import lombok.Data;

@Data
public class StudentVO {

  private Integer id;

  private String name;

  private String grade;

  private Double totalGrade;

  private Integer cId;

  private Date updated;

  private String city;
  private String type;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public Double getTotalGrade() {
    return totalGrade;
  }

  public void setTotalGrade(Double totalGrade) {
    this.totalGrade = totalGrade;
  }

  public Integer getcId() {
    return cId;
  }

  public void setcId(Integer cId) {
    this.cId = cId;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public String toString() {
    return "StudentVO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", grade='" + grade + '\'' +
        ", totalGrade=" + totalGrade +
        ", cId=" + cId +
        ", updated=" + updated +
        ", city='" + city + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
