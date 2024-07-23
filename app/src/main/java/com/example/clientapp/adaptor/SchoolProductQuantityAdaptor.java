package com.example.clientapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Product;
import com.example.clientapp.model.SchoolProduct;
import com.example.clientapp.retrofit.ProductApi;
import com.example.clientapp.retrofit.RetrofitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolProductQuantityAdaptor extends RecyclerView.Adapter<SchoolProductQuantityAdaptor.ViewHolder> {
    private List<SchoolProduct> schoolProductList;
    private Context context;
    private Map<String, Integer> schoolProductsMap;
    private List<Product> products;

    public SchoolProductQuantityAdaptor(List<SchoolProduct> schoolProductsList, List<Product> products, Context context) {
        this.schoolProductList = schoolProductsList;
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public SchoolProductQuantityAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product_quantity, parent, false);
        return new SchoolProductQuantityAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolProductQuantityAdaptor.ViewHolder holder, int position) {
        holder.productNameItem.setText(String.valueOf(products.get(position).getTitle()));
        holder.productQuantityItem.setText(String.valueOf(schoolProductList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return schoolProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameItem, productQuantityItem;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            productNameItem = itemView.findViewById(R.id.productNameItem);
            productQuantityItem = itemView.findViewById(R.id.productQuantityItem);
        }
    }
}
