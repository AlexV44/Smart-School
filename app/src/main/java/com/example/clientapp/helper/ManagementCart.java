package com.example.clientapp.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.clientapp.ChangeNumberItemsListener;
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
                n = i;
                break;
            }
        }
        if(existAlready) {
            listProduct.get(n).setNumberInCart(item.getNumberInCart());
            Toast.makeText(context,"Уже в коризне!", Toast.LENGTH_SHORT).show();
        } else {
            listProduct.add(item);
            Toast.makeText(context,"Добавлено в корзину!", Toast.LENGTH_SHORT).show();
        }
        tinyDB.putListObject("CartList", listProduct);
    }

    public ArrayList<Product> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberProduct(ArrayList<Product> productList, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        productList.get(position).setNumberInCart(productList.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", productList);
        changeNumberItemsListener.changed();
    }

    public void minusNumberProduct(ArrayList<Product> productList, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if(productList.get(position).getNumberInCart() == 1) {
           productList.remove(position);
        } else {
            productList.get(position).setNumberInCart(productList.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", productList);
        changeNumberItemsListener.changed();
    }

    public Double getTotalPrice() {
        ArrayList<Product> productList = getListCart();
        double price = 0;
        for (int i = 0; i < productList.size(); i++) {
            price = price + (productList.get(i).getPrice() * productList.get(i).getNumberInCart());
        }
        return price;
    }
}
