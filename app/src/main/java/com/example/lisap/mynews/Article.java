package com.example.lisap.mynews;

public class Article {
    private String photoUrl;
    private String title;
    private String description;
    private String date;

    //CONSTRUCTOR//
    public Article(){
    }

    //ARTICLE CONSTRUCTOR WITH ARGUMENTS//
    public Article(String photoUrl, String title, String description, String date) {
        this.photoUrl = photoUrl;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    //GETTERS AND SETTERS//
    public String getPhotoUrl(){
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

