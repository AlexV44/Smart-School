package com.example.clientapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.clientapp.R;
import com.example.clientapp.helper.ManagementCart;
import com.example.clientapp.model.Product;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn, titleDetail, priceDetail, descriptionDetail, numberOrderDetail;
    private ImageView plusBtn, minusBtn, picProduct;
    private Product object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (Product) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picProduct);

        titleDetail.setText(object.getTitle());
        priceDetail.setText(object.getPrice() + "Br");

        descriptionDetail.setText(object.getDescription());
        numberOrderDetail.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder += 1;
                numberOrderDetail.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder > 1) {
                    numberOrder -= 1;
                }
                numberOrderDetail.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setQuantity(numberOrder);
                managementCart.insertProduct(object);
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleDetail = findViewById(R.id.titleDetail);
        priceDetail = findViewById(R.id.priceDetail);
        descriptionDetail = findViewById(R.id.descriptionDetail);
        numberOrderDetail = findViewById(R.id.numberOrderDetail);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picProduct = findViewById(R.id.picProduct);
    }
}