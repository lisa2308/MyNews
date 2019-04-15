package com.example.lisap.mynews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.entities.Doc;
import com.example.lisap.mynews.utils.RecyclerViewHolderListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{

    List<Doc> docList;
    RecyclerViewHolderListener listener;

    public SearchAdapter(List<Doc> docList, RecyclerViewHolderListener listener){
        this.docList = docList;
        this.listener = listener;
    }

    public static class SearchHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        ImageView image;
        TextView description;

        public SearchHolder (View view) {
            super(view);
            title = view.findViewById(R.id.fragment_news_item_title);
            date = view.findViewById(R.id.fragment_news_item_date);
            image = view.findViewById(R.id.fragment_news_item_image);
            description = view.findViewById(R.id.fragment_news_item_description);

        }

    }

    @Override
    public SearchHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item,parent,false);
        return new SearchHolder(itemView);
    }

    //passe autant de fois qu'il y' a d'item//
    @Override
    public void onBindViewHolder(final SearchHolder holder, final int position) {

        //position liée à la ligne donc change toute seule//
        final Doc doc = docList.get(position);

        if (doc.getMultimedia()!=null) {
            if(!doc.getMultimedia().isEmpty()){
                Picasso.get().load("https://www.nytimes.com/" + doc.getMultimedia().get(0).getUrl()).into(holder.image);
            }
        }

        holder.title.setText(doc.getHeadline().getMain());
        holder.description.setText(doc.getSnippet());

        DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(doc.getPubDate()!=null) {
                Date date = dateFormatInput.parse(doc.getPubDate());
                DateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yy");
                holder.date.setText(dateFormatOutput.format(date));
            }
            else{
                holder.date.setText("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(holder,doc,position);
            }
        });


    }

    //Picasso.get().load("https://www.nytimes.com/" + doc.getMultimedia().get(0).getUrl()).into(holder.image);
    //        }

    //ITEM'S NUMBER//
    @Override
    public int getItemCount(){
        return docList.size();
    }
}

