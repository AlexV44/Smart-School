package com.example.clientapp.retrofit;

import com.example.clientapp.model.Order;
import com.example.clientapp.model.Product;
import com.example.clientapp.model.School;

import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SchoolApi {
    @GET("/api/schools")
    Call<List<School>> getAllSchools();

    @GET("/api/schools/{id}/products")
    Call<List<Product>> getProducts(@Path("id") int id);

    @GET("/api/schools/{id}/orders")
    Call<List<Order>> getOrders(@Path("id") int id);
}
