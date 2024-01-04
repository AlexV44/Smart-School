package com.example.clientapp.retrofit;

import com.example.clientapp.model.LunchRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LunchRequestApi {
    @Headers("Content-Type:application/json")
    @POST("/takelunchrequest")
    Call<LunchRequest> takeLunchRequest(@Body LunchRequest lunchRequest);
}
