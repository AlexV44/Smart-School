package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.model.Order;
import com.example.clientapp.retrofit.OrderApi;
import com.example.clientapp.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.ViewHolder>{

    private List<Order> orderList;

    public OrderAdaptor(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_orders, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.orderId.setText(String.valueOf(orderList.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId;
        RecyclerView recyclerViewOrdersMain;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            recyclerViewOrdersMain = itemView.findViewById(R.id.recyclerViewOrdersMain);
        }
    }
}
