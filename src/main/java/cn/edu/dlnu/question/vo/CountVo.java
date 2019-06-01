package cn.edu.dlnu.question.vo;

import lombok.Data;

@Data
public class CountVo {

  private String name;
  private String value;

  public CountVo(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
