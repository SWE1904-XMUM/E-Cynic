package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.Date;

public class Article {
    public Integer id;
    public String articleName;
    public String url;
    public Date articleDate;

    public Article(@Nullable Integer id, String articleName, String url, Date articleDate) {
        this.id = id;
        this.articleName = articleName;
        this.url = url;
        this.articleDate = articleDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", articleName='" + articleName + '\'' +
                ", url='" + url + '\'' +
                ", articleDate=" + articleDate +
                '}';
    }
}
