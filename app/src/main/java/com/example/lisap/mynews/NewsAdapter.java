package com.example.lisap.mynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    List<Article> articleList;

    public NewsAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    public static class NewsHolder extends RecyclerView.ViewHolder{
        public NewsHolder (View view) {super(view);}
    }

    @Override
    public NewsHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item,parent,false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsHolder holder, final int position){}

    @Override
    public int getItemCount(){
        return articleList.size();
    }
}

