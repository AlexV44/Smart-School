package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.model.Product;

import java.util.List;

public class OrderProductsAdaptor extends RecyclerView.Adapter<OrderProductsAdaptor.ViewHolder>{
    private List<Product> productList;

    public OrderProductsAdaptor(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order_products, parent, false);
        return new OrderProductsAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
                    holder.productName.setText(String.valueOf(productList.get(position).getTitle()));
                    holder.quantity.setText(String.valueOf(productList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
