package com.example.myapplication.MODEL;

public class Comment {
    private String id_user;
    private String name_user;
    private String img_user, content;

    public Comment() {
    }

    public Comment(String id_user, String name_user, String img_user, String content) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.img_user = img_user;
        this.content = content;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getImg_user() {
        return img_user;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
