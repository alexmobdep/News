package com.example.alex.largo.news.ui.fragment_current_article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.largo.news.R;

public class CurrentArticleFragment extends Fragment {

    public static final String EXTRA_ARTICLE_URL = "url_for_showing_chosen_article";
    private String mArticleUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleUrl = getArguments().getString(EXTRA_ARTICLE_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_article, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

    }
}
