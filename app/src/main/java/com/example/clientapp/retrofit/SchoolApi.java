package com.example.clientapp.retrofit;

import com.example.clientapp.model.School;
import com.example.clientapp.model.Smember;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolApi {
    @GET("/school/get-all")
    Call<List<School>> getAllSchools();
}
