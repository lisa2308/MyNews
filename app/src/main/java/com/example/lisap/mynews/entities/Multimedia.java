package com.example.lisap.mynews.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("url")
    @Expose
    private String url;

    public Multimedia() {
    }

    public String getUrl() {
        return url;
    }

}
