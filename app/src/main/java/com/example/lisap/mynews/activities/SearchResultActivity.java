package com.example.lisap.mynews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lisap.mynews.R;
import com.example.lisap.mynews.adapters.TopStoriesAdapter;
import com.example.lisap.mynews.entities.Root;
import com.example.lisap.mynews.utils.NewYorkTimesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        String q = getIntent().getExtras().getString("q");
        String fq = getIntent().getExtras().getString("fq");
        Toast.makeText(this,q + " " + fq, Toast.LENGTH_LONG).show();

        mRecyclerView = findViewById(R.id.activity_search_result_recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final NewYorkTimesService service = retrofit.create(NewYorkTimesService.class);

        Call<Root> rootCall = service.getSearchWithCategories(q, fq);
        rootCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                //CREATE RECYCLER ADAPTER//
               /* SearchResultAdapter searchResultAdapter = null;
                if (root != null) {
                    searchResultAdapter = new SearchResultAdapter(root.getResults());
                    //ASSOCIATE ADAPTER WITH RECYCLER//
                    mRecyclerView.setAdapter(topStoriesAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }*/
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {}
        });
    }

}
