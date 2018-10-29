package com.example.lisap.mynews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentNews extends Fragment {

    private static final String DRAWABLE = "image";
    private static final String MY_COLOR_KEY = "color";

    private int mImage;
    private int mColor;

    private RecyclerView mRecyclerView;


    // PASS DATA TO CONSTRUCTOR //
    public static FragmentNews newInstance(int image, int color) {
        FragmentNews f = new FragmentNews();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, image);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    // SELECT MOOD //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt(DRAWABLE);
            mColor = getArguments().getInt(MY_COLOR_KEY);
        } else {
            mImage = 0;
            mColor = Color.BLACK;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = v.findViewById(R.id.fragment_news_recycler_view);

        Article article = new Article("PhotoUrl", "Title", "Description", "Date");
        Article article1 = new Article("PhotoUrl", "Title", "Description", "Date");
        Article article2 = new Article("PhotoUrl", "Title", "Description", "Date");

        List<Article> articleList = new ArrayList<>();
        articleList.add(article);
        articleList.add(article1);
        articleList.add(article2);

        NewsAdapter newsAdapter = new NewsAdapter(articleList);
        mRecyclerView.setAdapter(newsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
}
