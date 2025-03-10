package com.example.clientapp.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String title;
    private String pic;
    private String description;
    private Double price;
    private int quantity;

    public Product() {
    }

    public Product(String title, String pic, String description, Double price) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
    }

    public Product(String title, String pic, String description, Double price, int quantity) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        if(pic != null) return pic;
        return "pop_1";
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
