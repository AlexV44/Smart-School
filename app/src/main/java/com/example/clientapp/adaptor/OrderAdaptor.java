package com.example.clientapp.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.model.Order;

import java.util.ArrayList;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.ViewHolder>{

    private ArrayList<Order> orderList;

    public OrderAdaptor(ArrayList<Order> orderList) {
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
        holder.orderId.setText(String.valueOf(1000 + orderList.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderRecyclerView;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
           // orderRecyclerView = itemView.findViewById(R.id.orderRecyclerView);
        }
    }
}
