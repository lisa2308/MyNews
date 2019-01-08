package com.example.lisap.mynews.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("kicker")
    @Expose
    private String kicker;

    public Headline() {
    }

    public String getMain() {
        return main;
    }
    public String getKicker() {
        return kicker;
    }
}
