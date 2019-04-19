package cn.edu.dlnu.question.entity;

import java.util.Date;
import lombok.Data;

public class City {
    private Integer id;

    private String name;

    private String artisticGrade;

    private String culturalGrade;

    private Date updated;

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
        this.name = name == null ? null : name.trim();
    }

    public String getArtisticGrade() {
        return artisticGrade;
    }

    public void setArtisticGrade(String artisticGrade) {
        this.artisticGrade = artisticGrade == null ? null : artisticGrade.trim();
    }

    public String getCulturalGrade() {
        return culturalGrade;
    }

    public void setCulturalGrade(String culturalGrade) {
        this.culturalGrade = culturalGrade == null ? null : culturalGrade.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}