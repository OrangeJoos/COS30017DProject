package com.example.babynamegenerator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostList {

    @SerializedName("popular_baby_names")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }
}
