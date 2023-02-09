package com.example.m214_retrofit_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("v3/5649c96e-3aa0-49bd-8b3f-bfc30bcea524")
    Call<List<Movie>> getMovies();

}
