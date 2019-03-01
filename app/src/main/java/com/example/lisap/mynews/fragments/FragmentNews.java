package com.example.lisap.mynews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lisap.mynews.adapters.MostPopularAdapter;
import com.example.lisap.mynews.utils.NewYorkTimesService;
import com.example.lisap.mynews.R;
import com.example.lisap.mynews.adapters.SearchAdapter;
import com.example.lisap.mynews.adapters.TopStoriesAdapter;
import com.example.lisap.mynews.entities.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentNews extends Fragment {

    private static final String POSITION = "position";

    private int mPosition;

    private RecyclerView mRecyclerView;

    // PASS DATA TO CONSTRUCTOR //
    public static FragmentNews newInstance(int position) {
        FragmentNews f = new FragmentNews();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        f.setArguments(args);
        return f;
    }

    // ASSOCIATE DATA WITH LOCAL VARIABLE //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(POSITION);
        } else {
            mPosition = 0;
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
        final NewYorkTimesService service = retrofit.create(NewYorkTimesService.class);

        if(mPosition ==0) {
            Call<Root> rootCall = service.getTopStories();
            rootCall.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    Root root = response.body();

                    //CREATE RECYCLER ADAPTER//
                    TopStoriesAdapter topStoriesAdapter = null;
                    if (root != null) {
                        topStoriesAdapter = new TopStoriesAdapter(root.getResults());

                        //ASSOCIATE ADAPTER WITH RECYCLER//
                        mRecyclerView.setAdapter(topStoriesAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    }

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });
        }

        else if (mPosition ==1) {
            Call<Root> rootCall = service.getMostPopular();
            rootCall.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {

                    Root root = response.body();

                    //CREATE RECYCLER ADAPTER//
                    MostPopularAdapter mostPopularAdapter = null;
                    if (root != null) {
                        mostPopularAdapter = new MostPopularAdapter(root.getResults());

                        //ASSOCIATE ADAPTER WITH RECYCLER//
                        mRecyclerView.setAdapter(mostPopularAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    }

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });
        }
        else {
            Call<Root> rootCall = service.getSearch("Arts");
            rootCall.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    Root root = response.body();

                    //CREATE RECYCLER ADAPTER//
                    SearchAdapter searchAdapter = null;
                    if (root != null) {
                        searchAdapter = new SearchAdapter(root.getResponse().getDocs());

                        //ASSOCIATE ADAPTER WITH RECYCLER//
                        mRecyclerView.setAdapter(searchAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                    }
                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });
        }

        return v;
    }
}
