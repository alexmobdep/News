package com.example.alex.largo.news.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("Status")
    String status;

    @SerializedName("source")
    String source;

    @SerializedName("sortBy")
    String sortBy;

    @SerializedName("articles")
    List<Article> articlesList;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<Article> getArticlesList() {
        return articlesList;
    }
}
