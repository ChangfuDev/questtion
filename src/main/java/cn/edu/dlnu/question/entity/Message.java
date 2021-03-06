package cn.edu.dlnu.question.entity;

public class Message {
    private Integer id;

    private String title;

    private String cotent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent == null ? null : cotent.trim();
    }

    public Message(String title, String cotent) {
        this.title = title;
        this.cotent = cotent;
    }

    public Message() {
    }

    public Message(Integer id, String title, String cotent) {
        this.id = id;
        this.title = title;
        this.cotent = cotent;
    }
}