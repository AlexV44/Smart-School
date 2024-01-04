package com.example.clientapp.retrofit;

import com.example.clientapp.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderApi {
    @Headers("Content-Type:application/json")
    @POST("/takeorder")
    Call<Order> takeorder(@Body Order order);

    @GET("/api/orders")
    Call<List<Order>> getAllSchoolOrders();

    @POST("/deleteorder")
    Call<Order> deleteOrder(@Body Order order);

}
