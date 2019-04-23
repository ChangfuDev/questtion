package cn.edu.dlnu.question.vo;

import lombok.Data;

@Data
public class GradeLineVO {
  private String name;
  private double grade;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getGrade() {
    return grade;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "GradeLineVO{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        '}';
  }
}
