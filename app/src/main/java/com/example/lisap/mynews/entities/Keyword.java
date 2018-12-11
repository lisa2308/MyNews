
package com.example.lisap.mynews.entities;


public class Keyword {

    private String name;
    private String value;
    private Integer rank;
    private String major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", rank=" + rank +
                ", major='" + major + '\'' +
                '}';
    }
}
