package com.example.clientapp.retrofit;

import com.example.clientapp.model.Product;
import com.example.clientapp.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
   /* @GET("/products/{id}/products")
    Call<List<Product>> getAllProducts(@Path("id") int id);*/

    @GET("/getproducts")
    Call<List<Product>> getProduct();

    @GET("/products/{id}")
    Call<List<Product>> getProductBySchool(@Path("id") int id);
}
