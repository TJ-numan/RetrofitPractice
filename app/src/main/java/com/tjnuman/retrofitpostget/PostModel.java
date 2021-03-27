package com.tjnuman.retrofitpostget;

import com.google.gson.annotations.SerializedName;

public class PostModel {
    private Integer id;
    private int userId;
    private String titlel;
    @SerializedName("body")
    private String text;

    public PostModel(int userId, String titlel, String text) {
        this.userId = userId;
        this.titlel = titlel;
        this.text = text;
    }

    public PostModel() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitlel(String titlel) {
        this.titlel = titlel;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitlel() {
        return titlel;
    }

    public String getText() {
        return text;
    }
}
