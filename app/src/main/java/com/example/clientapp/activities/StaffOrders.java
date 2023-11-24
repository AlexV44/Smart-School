package com.example.clientapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.CategoryAdaptor;
import com.example.clientapp.adaptor.OrderAdaptor;
import com.example.clientapp.adaptor.ProductAdaptor;
import com.example.clientapp.domain.CategoryDomain;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Product;

import java.util.ArrayList;

public class StaffOrders extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_orders);
        recyclerViewOrdersList();
    }

    private void recyclerViewOrdersList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StaffOrders.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrdersList = findViewById(R.id.recyclerViewOrdersMain);
        recyclerViewOrdersList.setLayoutManager(linearLayoutManager);

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(12));
        orders.add(new Order(2));

        adapter = new OrderAdaptor(orders);
        recyclerViewOrdersList.setAdapter(adapter);
    }
}