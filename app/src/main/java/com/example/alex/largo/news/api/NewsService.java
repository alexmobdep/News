package com.example.alex.largo.news.api;


import com.example.alex.largo.news.model.NewsResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NewsService {

    Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/")
            .build();

    @GET("v1/articles?")
    Observable<NewsResponse> topNews(@Query("source")String source,
                                     @Query("sortBy") String sortBy,
                                     @Query("apiKey")String apiKey);
}
