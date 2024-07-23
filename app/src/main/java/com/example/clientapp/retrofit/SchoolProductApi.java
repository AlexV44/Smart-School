package com.example.clientapp.retrofit;

import com.example.clientapp.model.Product;
import com.example.clientapp.model.SchoolProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SchoolProductApi {
    @GET("/schoolProducts/{id}/schoolProducts")
    Call<List<SchoolProduct>> getSchoolProducts(@Path("id") int id);
}
