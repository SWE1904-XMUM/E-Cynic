package com.example.e_cynic.db.mapper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.e_cynic.entity.Article;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ArticleMapper {
    public static Article mapCursorToOneArticle(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        return (cursor.moveToFirst()) ? Mapper.mapCursorToOne(cursor, Article.class) : null;
    }

    public static List<Article> mapCursorToArticles(Cursor cursor) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return (cursor.moveToFirst()) ? Mapper.mapCursorToMany(cursor, Article.class) : null;
    }

    public static ContentValues mapArticleToContentValues(Article article) throws IllegalAccessException {
        return Mapper.mapEntityToContentValues(article);
    }
}
