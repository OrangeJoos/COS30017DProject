package com.example.babynamegenerator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BabyList {

    @SerializedName("popular_baby_names")
    private List<Baby> babies;

    public List<Baby> getBabies() {
        return babies;
    }
}
