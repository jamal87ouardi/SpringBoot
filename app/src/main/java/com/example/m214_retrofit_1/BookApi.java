package com.example.m214_retrofit_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookApi {

    @GET("v3/fb184368-8cbe-48d6-ae6c-4a407f25b465")
    Call<List<Book>> getBooks();

}
