package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.entity.Article;

public class ArticleDatabase
{
    public static final String articlesTable = "articles";
    public static final String articleName = "articleName";
    public static final String url = "url";
    public static final String articleDate = "articleDate";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static void insertArticle(Article article)
    {
        ContentValues cv = new ContentValues();
        cv.put(articleName,article.articleName);
        cv.put(url,article.url);
        cv.put(articleDate, String.valueOf(article.articleDate));

        long result = db.insert(articlesTable, null, cv);
    }
}
