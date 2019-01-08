package com.example.lisap.mynews;

import com.example.lisap.mynews.entities.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesService {

    //&q=query
    @GET("svc/search/v2/articlesearch.json?api-key=b5e7d03283ad41eea804c42ecd13e541")
    Call<Root> getSearch(@Query("q") String query);
    //Url qui va etre séparée en 2, la 1ère partie on utilise tt le temps mais la 2 sera compilable ex "search, top stories, most popular"
    //https://api.nytimes.com/

    @GET("svc/topstories/v2/home.json?api-key=b5e7d03283ad41eea804c42ecd13e541")
    Call<Root> getTopStories();

    @GET("svc/mostpopular/v2/mostviewed/World/7.json?api-key=b5e7d03283ad41eea804c42ecd13e541")
    Call<Root> getMostPopular();
}
