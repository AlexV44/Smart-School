package com.example.clientapp.retrofit;

import com.example.clientapp.model.Order;
import com.example.clientapp.model.Smember;

import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {
    @Headers("Content-Type:application/json")
    @POST("/takeorder")
    Call<Order> takeorder(@Body Order order);

    @GET("/api/orders")
    Call<List<Order>> getAllSchoolOrders();

    @POST("/deleteorder")
    Call<Order> deleteOrder(@Body Order order);

}
