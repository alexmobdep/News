package com.example.alex.largo.news.ui.fragment_list_news;


import com.example.alex.largo.news.api.NewsService;
import com.example.alex.largo.news.db.RealmHelper;
import com.example.alex.largo.news.model.Article;
import com.example.alex.largo.news.model.NewsResponse;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class ListNewsPresenterImpl implements ListNewsPresenter {

    private static final String SORT_BY = "top";
    private static final String API_KEY = "1385d1f14e534e10a1013e622f4ada2c";

    private ListNewsView mListNewsView;
    private String mPortalKey;

    ListNewsPresenterImpl(ListNewsView listNewsView, String portalKey) {
        this.mListNewsView = listNewsView;
        mPortalKey = portalKey;
    }

    @Override
    public void makeRequest() {
        NewsService service = NewsService.retrofit.create(NewsService.class);
        Observable<NewsResponse> observable = service.topNews(mPortalKey, SORT_BY, API_KEY);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onCompleted() {
                        mListNewsView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListNewsView.showErrorDialog();
                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        //сохраняем данные в базу
                        List<Article> articles = newsResponse.getArticlesList();
                        RealmHelper realmHelper = new RealmHelper();
                        realmHelper.addNews(articles, mPortalKey);
                        mListNewsView.inflateData();
                    }
                });
    }

    @Override
    public void onDestroy() {
        mListNewsView = null;
    }
}
