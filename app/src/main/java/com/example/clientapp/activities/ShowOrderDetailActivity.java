package com.example.clientapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.OrderProductsAdaptor;
import com.example.clientapp.manager.OrderSessionManager;

public class ShowOrderDetailActivity extends AppCompatActivity {
    private RecyclerView.Adapter orderProductsAdapter;
    private RecyclerView recyclerViewOrderProductsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_detail);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowOrderDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderProductsList = findViewById(R.id.recyclerViewOrderProductsList);
        recyclerViewOrderProductsList.setLayoutManager(linearLayoutManager);
        orderProductsAdapter = new OrderProductsAdaptor(OrderSessionManager.getInstance().getOrder().getProducts());
        recyclerViewOrderProductsList.setAdapter(orderProductsAdapter);
    }
}