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

import com.example.lisap.mynews.entities.Result;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    // ASSOCIATE DATA WITH LOCAL VARIABLE //
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //enqueue liste attente
        NewYorkTimesService service = retrofit.create(NewYorkTimesService.class);
        Call<Result> resultCall = service.getResult("trump");
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                //CREATE RECYCLER ADAPTER//
                NewsAdapter newsAdapter = new NewsAdapter(result.getResponse().getDocs());
                //ASSOCIATE ADAPTER WITH RECYCLER//
                mRecyclerView.setAdapter(newsAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return v;
    }
}
