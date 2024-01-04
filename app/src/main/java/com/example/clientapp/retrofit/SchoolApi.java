package com.example.clientapp.retrofit;

import com.example.clientapp.model.LunchRequest;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Product;
import com.example.clientapp.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SchoolApi {
    @GET("/api/schools")
    Call<List<School>> getAllSchools();

    @GET("/api/schools/{id}/products")
    Call<List<Product>> getProducts(@Path("id") int id);

    @GET("/api/schools/{id}/orders")
    Call<List<Order>> getOrders(@Path("id") int id);

    @GET("/api/schools/{id}/lunchrequests")
    Call<List<LunchRequest>> getLunchRequests(@Path("id") int id);
}
