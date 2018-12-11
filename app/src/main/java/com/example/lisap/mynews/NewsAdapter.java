package com.example.lisap.mynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lisap.mynews.entities.Doc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    List<Doc> docList;

    public NewsAdapter(List<Doc> docList){
        this.docList = docList;
    }

    public static class NewsHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        ImageView image;
        TextView description;

        public NewsHolder (View view) {
            super(view);
            title = view.findViewById(R.id.fragment_news_item_title);
            date = view.findViewById(R.id.fragment_news_item_date);
            image = view.findViewById(R.id.fragment_news_item_image);
            description = view.findViewById(R.id.fragment_news_item_description);

        }

    }

    @Override
    public NewsHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item,parent,false);
        return new NewsHolder(itemView);
    }

    //passe autant de fois qu'il y' a d'item//
    @Override
    public void onBindViewHolder(final NewsHolder holder, final int position) {

        //position liée à la ligne donc change toute seule//
        Doc doc = docList.get(position);

        if (doc.getMultimedia()!=null) {
            Picasso.get().load("https://www.nytimes.com/" + doc.getMultimedia().get(0).getUrl()).into(holder.image);
        }

        holder.title.setText(doc.getHeadline().getMain());
        holder.description.setText(doc.getSnippet());
        holder.date.setText(doc.getPubDate());

        System.out.println(doc.getPubDate());
    }

    //ITEM'S NUMBER//
    @Override
    public int getItemCount(){
        return docList.size();
    }
}

