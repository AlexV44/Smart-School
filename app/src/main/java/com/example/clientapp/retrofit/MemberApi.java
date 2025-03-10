package com.example.clientapp.retrofit;

import com.example.clientapp.model.LoginRequest;
import com.example.clientapp.model.SignupRequest;
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

    @POST("/signup")
    Call<Smember> signup(@Body SignupRequest request);

    @POST("/login")
    Call<Smember> login(@Body LoginRequest request);

    @POST("/member/setstaff")
    Call<Smember> setStaffById(@Body int id);

    @POST("/member/setbalance")
    Call<Double> updateUserBalance(@Body Smember member);

    @POST("/getuser")
    Call<Smember> getUserById(@Body int id);

    @POST("/member/setteacher")
    Call<Smember> setTeacherById(@Body int id);

    @POST("/member/setelder")
    Call<Smember> setElderById(@Body int id);
}
