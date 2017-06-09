package com.example.alex.largo.news.ui.fragment_list_news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.alex.largo.news.R;
import com.example.alex.largo.news.model.Article;
import com.example.alex.largo.news.ui.fragment_current_article.CurrentArticleFragment;

import io.realm.Realm;
import io.realm.RealmResults;


public class ListNewsFragment extends Fragment implements ListNewsView, CurrentArticle {

    public static final String NEWS_KEY = "news_key";
    private String mPortalKey;

    private Realm mRealm;

    private RecyclerView mNewsRecyclerView;
    private ProgressBar mProgressBar;

    private ListNewsPresenter mListNewsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPortalKey = getArguments().getString(NEWS_KEY);
        mRealm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        initViews(view);
        mListNewsPresenter = new ListNewsPresenterImpl(this, mPortalKey);
        mListNewsPresenter.makeRequest();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListNewsPresenter.onDestroy();
    }

    private void initViews(View view) {
        mNewsRecyclerView = (RecyclerView) view.findViewById(R.id.list_news_rv);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }


    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorDialog() {
        //TODO create alert for showing error if data didn't receive
    }

    @Override
    public void inflateData() {
        RealmResults<Article> savedArticles = getDataFromDataBase();

        NewsAdapter newsAdapter = new NewsAdapter(savedArticles, getContext(), this);
        mNewsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void goToChosenArticle(String url) {
        CurrentArticleFragment articleFragment = new CurrentArticleFragment();
        Bundle args = new Bundle();
        args.putString(CurrentArticleFragment.EXTRA_ARTICLE_URL, url);
        articleFragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, articleFragment)
                .addToBackStack(null).commit();
    }

    private RealmResults<Article> getDataFromDataBase(){
       return mRealm.where(Article.class)
                .equalTo("portalKey", mPortalKey)
                .findAll();
    }
}
