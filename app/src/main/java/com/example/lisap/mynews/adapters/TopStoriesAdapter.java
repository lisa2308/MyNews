package com.example.lisap.mynews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.entities.Result;
import com.example.lisap.mynews.utils.RecyclerViewHolderListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoriesHolder>{

    List<Result> resultList;
    RecyclerViewHolderListener listener;

    public TopStoriesAdapter(List<Result> resultList, RecyclerViewHolderListener listener){
        this.resultList = resultList;
        this.listener = listener;
    }

    public static class TopStoriesHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        ImageView image;
        TextView description;

        public TopStoriesHolder (View view) {
            super(view);
            title = view.findViewById(R.id.fragment_news_item_title);
            date = view.findViewById(R.id.fragment_news_item_date);
            image = view.findViewById(R.id.fragment_news_item_image);
            description = view.findViewById(R.id.fragment_news_item_description);

        }

    }

    @Override
    public TopStoriesHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item,parent,false);
        return new TopStoriesHolder(itemView);
    }

    //passe autant de fois qu'il y' a d'item//
    @Override
    public void onBindViewHolder(final TopStoriesHolder holder, final int position) {

        //position liée à la ligne donc change toute seule//
        final Result result = resultList.get(position);

        if (result.getMultimedia()!=null) {
            if(!result.getMultimedia().isEmpty()){
                Picasso.get().load(result.getMultimedia().get(0).getUrl()).into(holder.image);
            }
        }

        holder.title.setText(result.getTitle());
        holder.description.setText(result.getDescription());

        DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormatInput.parse(result.getPublishedDate());
            DateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yy");
            holder.date.setText(dateFormatOutput.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(holder,result,position);
            }
        });
    }

    //Picasso.get().load("https://www.nytimes.com/" + result.getMultimedia().get(0).getUrl()).into(holder.image);
    //        }

    //ITEM'S NUMBER//
    @Override
    public int getItemCount(){
        return resultList.size();
    }
}

