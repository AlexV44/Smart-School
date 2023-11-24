package com.example.clientapp.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clientapp.ChangeNumberItemsListener;
import com.example.clientapp.R;
import com.example.clientapp.helper.ManagementCart;
import com.example.clientapp.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {
    private ArrayList<Product> productsList;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdaptor(ArrayList<Product> productsList, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.productsList = productsList;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(productsList.get(position).getTitle());
        holder.itemPrice.setText(String.valueOf(productsList.get(position).getPrice()));

        BigDecimal bigDecimalPrice = BigDecimal.valueOf(productsList.get(position).getPrice());
        BigDecimal bigDecimalNumberInCart = BigDecimal.valueOf(productsList.get(position).getNumberInCart());
        holder.totalItemPrice.setText(String.valueOf(bigDecimalPrice.multiply(bigDecimalNumberInCart)));

        holder.num.setText(String.valueOf(productsList.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(productsList.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(view -> onPlusClick(position));
        holder.minusItem.setOnClickListener(view -> onMinusClick(position));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    private void onPlusClick(int position) {
        managementCart.plusNumberProduct(productsList, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        });
    }

    private void onMinusClick(int position) {
        managementCart.minusNumberProduct(productsList, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, itemPrice, num, totalItemPrice;
        ImageView pic, plusItem, minusItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.orderId);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            totalItemPrice = itemView.findViewById(R.id.totalItemPrice);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemText);
        }
    }
}
