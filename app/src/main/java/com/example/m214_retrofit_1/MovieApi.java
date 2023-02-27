package com.example.m214_retrofit_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("v3/45366348-4c96-4fe6-9ea2-5d68d8c426ea")
    Call<List<Movie>> getMovies();

}
