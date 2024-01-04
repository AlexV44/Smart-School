package com.example.clientapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.OrderAdaptor;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Order;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffOrders extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewOrdersList;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_orders);
        setRecyclerViewOrdersList();
    }

    private void setRecyclerViewOrdersList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StaffOrders.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrdersList = findViewById(R.id.recyclerViewOrdersMain);
        recyclerViewOrdersList.setLayoutManager(linearLayoutManager);

        RetrofitService retrofitService = new RetrofitService();
        SchoolApi schoolApi = retrofitService.getRetrofit().create(SchoolApi.class);
        orders = new ArrayList<>();
        adapter = new OrderAdaptor(orders);
        recyclerViewOrdersList.setAdapter(adapter);
        schoolApi.getOrders(UserSessionManager.getInstance().getSmember().getSchoolId()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders.clear();
                orders.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
            }
        });
    }
}