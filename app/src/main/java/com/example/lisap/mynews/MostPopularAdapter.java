package com.example.lisap.mynews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lisap.mynews.entities.Result;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularHolder>{

    List<Result> resultList;

    public MostPopularAdapter(List<Result> resultList){
        this.resultList = resultList;
    }

    public static class MostPopularHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        ImageView image;
        TextView description;

        public MostPopularHolder (View view) {
            super(view);
            title = view.findViewById(R.id.fragment_news_item_title);
            date = view.findViewById(R.id.fragment_news_item_date);
            image = view.findViewById(R.id.fragment_news_item_image);
            description = view.findViewById(R.id.fragment_news_item_description);

        }

    }

    @Override
    public MostPopularHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item,parent,false);
        return new MostPopularHolder(itemView);
    }

    //passe autant de fois qu'il y' a d'item//
    @Override
    public void onBindViewHolder(final MostPopularHolder holder, final int position) {

        //position liée à la ligne donc change toute seule//
        Result result = resultList.get(position);

        if (result.getMedia()!=null) {
            if(!result.getMedia().isEmpty()){
                Picasso.get().load(result.getMedia().get(0).getMetaMedia().get(0).getUrl()).into(holder.image);
            }
        }

        holder.title.setText(result.getTitle());
        holder.description.setText(result.getDescription());

        DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(result.getPublishedDate()!=null) {
                Date date = dateFormatInput.parse(result.getPublishedDate());
                DateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yy");
                holder.date.setText(dateFormatOutput.format(date));
            }
            else{
                holder.date.setText("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    //Picasso.get().load("https://www.nytimes.com/" + doc.getMultimedia().get(0).getUrl()).into(holder.image);
    //        }

    //ITEM'S NUMBER//
    @Override
    public int getItemCount(){
        return resultList.size();
    }
}

