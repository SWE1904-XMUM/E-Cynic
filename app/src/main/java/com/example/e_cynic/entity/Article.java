package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.Date;

public class Article {
    public Integer articleId;
    public String articleName;
    public String url;
    public Date articleDate;

    public Article () {}
    public Article(@Nullable Integer articleId, String articleName, String url, Date articleDate) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.url = url;
        this.articleDate = articleDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", url='" + url + '\'' +
                ", articleDate=" + articleDate +
                '}';
    }
}
