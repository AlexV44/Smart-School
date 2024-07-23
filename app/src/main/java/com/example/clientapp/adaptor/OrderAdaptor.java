package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.activities.ShowOrderDetailActivity;
import com.example.clientapp.manager.OrderSessionManager;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Smember;
import com.example.clientapp.retrofit.MemberApi;

import java.util.List;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderId.setText(String.valueOf(orderList.get(position).getId()));
        holder.orderTime.setText(String.valueOf(orderList.get(position).getTime()));
        holder.orderProductsCount.setText(String.valueOf(orderList.get(position).getProducts().size()));
        holder.mainOrderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowOrderDetailActivity.class);
                OrderSessionManager.getInstance().setOrder(orderList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderProductsCount, orderTime;
        ConstraintLayout mainOrderLayout;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderProductsCount = itemView.findViewById(R.id.orderProductsCount);
            mainOrderLayout = itemView.findViewById(R.id.constraintLayOrder);
            orderTime = itemView.findViewById(R.id.orderTime);
        }
    }
}
