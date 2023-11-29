package com.example.clientapp.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.clientapp.ChangeNumberItemsListener;
import com.example.clientapp.model.Product;

import java.math.BigDecimal;
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
            listProduct.get(n).setQuantity(item.getQuantity());
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
        productList.get(position).setQuantity(productList.get(position).getQuantity() + 1);
        tinyDB.putListObject("CartList", productList);
        changeNumberItemsListener.changed();
    }

    public void minusNumberProduct(ArrayList<Product> productList, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if(productList.get(position).getQuantity() == 1) {
           productList.remove(position);
        } else {
            productList.get(position).setQuantity(productList.get(position).getQuantity() - 1);
        }
        tinyDB.putListObject("CartList", productList);
        changeNumberItemsListener.changed();
    }

    public Double getTotalPrice() {
        ArrayList<Product> productList = getListCart();
        BigDecimal bigPrice = new BigDecimal(0.);
        for (int i = 0; i < productList.size(); i++) {
            BigDecimal bigDecimal1 = BigDecimal.valueOf(productList.get(i).getPrice());
            BigDecimal bigDecimal2 = BigDecimal.valueOf(productList.get(i).getQuantity());
            bigPrice = bigPrice.add(bigDecimal1.multiply(bigDecimal2));
        }
        return bigPrice.doubleValue();
    }
}
