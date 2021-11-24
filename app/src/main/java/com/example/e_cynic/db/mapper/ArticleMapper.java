package com.example.e_cynic.db.mapper;

import android.database.Cursor;

import com.example.e_cynic.entity.Article;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleMapper {
    public static Article mapCursorToOneArticle(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (cursor == null) {
            return null;
        }

        Article article = Article.class.getDeclaredConstructor().newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = article.getClass().getDeclaredField(column_names.get(i));
            field.set(article, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return article;
    }

    public static List<Article> mapCursorToArticles(Cursor cursor) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Article> articleList = new ArrayList<>();

        do {
            articleList.add(mapCursorToOneArticle(cursor));
        } while (cursor.moveToNext());

        return articleList;
    }

}
