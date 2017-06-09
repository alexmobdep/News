package com.example.alex.largo.news.db;


import com.example.alex.largo.news.model.Article;

import java.util.List;

import io.realm.Realm;

public class RealmHelper {

    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    public void addNews(final List<Article> articlesList, final String portalKey) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Article article : articlesList) {
                    Article savedArticle = mRealm.createObject(Article.class);
                    savedArticle.setPortalKey(portalKey);
                    savedArticle.setTitle(article.getTitle());
                    savedArticle.setAuthor(article.getAuthor());
                    savedArticle.setDescription(article.getDescription());
                    savedArticle.setUrl(article.getUrl());
                    savedArticle.setUrlToImage(article.getUrlToImage());
                    savedArticle.setPublishedAt(article.getPublishedAt());
                }
            }
        });
    }

}
