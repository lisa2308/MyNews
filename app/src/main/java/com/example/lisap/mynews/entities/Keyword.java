package com.example.lisap.mynews.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("value")
    @Expose
    private String value;

    public Keyword() {
    }
    public String getValue() {
        return value;
    }


}
