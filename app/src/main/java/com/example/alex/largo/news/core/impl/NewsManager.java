package com.example.alex.largo.news.core.impl;

import com.example.alex.largo.news.api.NewsService;
import com.example.alex.largo.news.model.NewsResponse;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsManager implements com.example.alex.largo.news.core.NewsManager {

    private NewsService mService;

    public NewsManager(NewsService service) {
        mService = service;
    }

    @Override
    public void getTopNews(String portalKey) {
        Observable<NewsResponse> observable = mService.topNews(portalKey, "top", "1385d1f14e534e10a1013e622f4ada2c");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        String a = "a";
                    }
                });
    }
}
