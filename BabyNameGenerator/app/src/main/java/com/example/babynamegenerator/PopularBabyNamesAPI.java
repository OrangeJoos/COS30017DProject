package com.example.babynamegenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PopularBabyNamesAPI {

    @Headers({"apikey: 4dfe7de7-d803-412b-9298-d13cc8c51aad"})
    @GET("popular-baby-names")
    Call<PostList> getPosts(
            @Query("year") int year,
            @Query("sex") String gender,
            @Query("position") int position
    );
}
