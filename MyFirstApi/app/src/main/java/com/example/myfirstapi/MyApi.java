package com.example.myfirstapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface MyApi {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")//marvel is the API name
    Call<List<Hero>> getHeroes();
}
