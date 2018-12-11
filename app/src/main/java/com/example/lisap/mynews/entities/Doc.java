
package com.example.lisap.mynews.entities;

import java.util.List;

public class Doc {

    private String webUrl;
    private String snippet;
    private String printPage;
    private Blog blog;
    private String source;
    private List<Multimedium> multimedia = null;
    private Headline headline;
    private List<Keyword> keywords = null;
    private String pubDate;
    private String documentType;
    private String typeOfMaterial;
    private String id;
    private Integer wordCount;
    private Double score;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "webUrl='" + webUrl + '\'' +
                ", snippet='" + snippet + '\'' +
                ", printPage='" + printPage + '\'' +
                ", blog=" + blog +
                ", source='" + source + '\'' +
                ", multimedia=" + multimedia +
                ", headline=" + headline +
                ", keywords=" + keywords +
                ", pubDate='" + pubDate + '\'' +
                ", documentType='" + documentType + '\'' +
                ", typeOfMaterial='" + typeOfMaterial + '\'' +
                ", id='" + id + '\'' +
                ", wordCount=" + wordCount +
                ", score=" + score +
                '}';
    }
}
