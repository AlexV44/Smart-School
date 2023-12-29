package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                    holder.productTitle.setText(String.valueOf(productList.get(position).getTitle()));
                    holder.orderProductsCount.setText(String.valueOf(productList.get(position).getQuantity()));

        int drawableResourceId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(productList.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTitle, orderProductsCount;
        private ImageView pic;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.productTitle);
            orderProductsCount = itemView.findViewById(R.id.orderProductsCount);
            pic = itemView.findViewById(R.id.picOrder);
        }
    }
}
