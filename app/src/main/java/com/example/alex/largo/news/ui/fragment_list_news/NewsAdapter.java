package com.example.alex.largo.news.ui.fragment_list_news;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alex.largo.news.R;
import com.example.alex.largo.news.model.Article;

import java.util.List;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> mArticles;
    private Context mContext;
    private CurrentArticle mCurrentArticle;

    NewsAdapter(List<Article> articles, Context context, CurrentArticle currentArticle) {
        mArticles = articles;
        mContext = context;
        mCurrentArticle = currentArticle;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout root;
        ImageView posterImageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView authorTextView;

        ViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.news_img);
            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_tv);
            authorTextView = (TextView) itemView.findViewById(R.id.author_tv);
            root = (ConstraintLayout) itemView.findViewById(R.id.root);
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new NewsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        Article article = mArticles.get(position);

        String title = article.getTitle();
        String description = article.getDescription();
        String author = article.getAuthor();
        String urlToImage = article.getUrlToImage();
        final String url = article.getUrl();

        holder.titleTextView.setText(title);
        holder.authorTextView.setText(author);
        holder.descriptionTextView.setText(description);

        Glide.with(mContext)
                .load(urlToImage)
                .into(holder.posterImageView);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentArticle.goToChosenArticle(url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
