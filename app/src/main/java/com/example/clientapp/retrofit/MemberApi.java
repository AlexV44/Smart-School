package com.example.clientapp.retrofit;

import com.example.clientapp.model.Smember;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MemberApi {
    @GET("/member/get-all")
    Call<List<Smember>> getAllMembers();

    @POST("/member/save")
    Call<Smember> save(@Body Smember member);
}
