package com.example.clientapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.clientapp.R;
import com.example.clientapp.adaptor.SchoolProductQuantityAdaptor;
import com.example.clientapp.manager.UserSessionManager;
import com.example.clientapp.model.Product;
import com.example.clientapp.model.SchoolProduct;
import com.example.clientapp.retrofit.ProductApi;
import com.example.clientapp.retrofit.RetrofitService;
import com.example.clientapp.retrofit.SchoolProductApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsInStockActivity extends AppCompatActivity {

    private List<SchoolProduct> schoolProductList;
    private List<Product> productsList;
    private RecyclerView recyclerViewProductQuantity;
    private SchoolProductQuantityAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_in_stock);
        initViews();
        setRecyclerViewSchoolProductsList();
    }

    private void initViews() {
        recyclerViewProductQuantity = findViewById(R.id.recyclerViewProductQuantity);
    }

    private void setRecyclerViewSchoolProductsList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewProductQuantity.setLayoutManager(linearLayoutManager);
        schoolProductList = new ArrayList<>();
        productsList = new ArrayList<>();

        RetrofitService retrofitService = new RetrofitService();
        SchoolProductApi schoolProductApi = retrofitService.getRetrofit().create(SchoolProductApi.class);
        ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

        adapter = new SchoolProductQuantityAdaptor(schoolProductList, productsList, this);
        recyclerViewProductQuantity.setAdapter(adapter);

        schoolProductApi.getSchoolProducts(UserSessionManager.getInstance().getSmember().getSchoolId()).enqueue(new Callback<List<SchoolProduct>>() {
            @Override
            public void onResponse(Call<List<SchoolProduct>> call, Response<List<SchoolProduct>> response) {
                schoolProductList.clear();
                schoolProductList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SchoolProduct>> call, Throwable t) {

            }
        });

        productApi.getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productsList.clear();
                productsList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}