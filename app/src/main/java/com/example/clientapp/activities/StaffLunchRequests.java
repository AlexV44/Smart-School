package com.example.clientapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.LunchRequestAdaptor;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.LunchRequest;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffLunchRequests extends AppCompatActivity {
    private RecyclerView recyclerViewLunchRequests;
    private RecyclerView.Adapter adapter;
    private List<LunchRequest> lunchRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_lunch_requests);
        setRecyclerViewLunchRequestsList();
    }

    private void setRecyclerViewLunchRequestsList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StaffLunchRequests.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewLunchRequests = findViewById(R.id.recyclerViewLunchRequests);
        recyclerViewLunchRequests.setLayoutManager(linearLayoutManager);

        RetrofitService retrofitService = new RetrofitService();
        SchoolApi schoolApi = retrofitService.getRetrofit().create(SchoolApi.class);
        lunchRequests = new ArrayList<>();
        adapter = new LunchRequestAdaptor(lunchRequests);
        recyclerViewLunchRequests.setAdapter(adapter);
        schoolApi.getLunchRequests(UserSessionManager.getInstance().getSmember().getSchoolId()).enqueue(new Callback<List<LunchRequest>>() {
            @Override
            public void onResponse(Call<List<LunchRequest>> call, Response<List<LunchRequest>> response) {
                lunchRequests.clear();
                lunchRequests.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LunchRequest>> call, Throwable t) {

            }
        });
    }
}