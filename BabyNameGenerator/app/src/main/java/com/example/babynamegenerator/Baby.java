package com.example.babynamegenerator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Baby {


    @SerializedName("position")
    private int position;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("count")
    private int count;
    @SerializedName("year")
    private int year;


    public int getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }

    public int getYear() {
        return year;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }
}