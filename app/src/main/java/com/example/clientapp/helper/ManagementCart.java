package com.example.clientapp.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.clientapp.model.Product;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertProduct(Product item) {
        ArrayList<Product> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            if(listProduct.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                break;
            }
        }
        if(existAlready) {
            listProduct.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listProduct.add(item);
            tinyDB.putListObject("CartList", listProduct);
            Toast.makeText(context,"Added to your Cart!", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Product> getListCart() {
        return tinyDB.getListObject("CartList");
    }
}
