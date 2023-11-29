package com.example.clientapp.model;

import java.util.List;

public class Order {
    private int id;
    private List<Product> products;
    private int memberId;
    private int schoolId;


    public Order(){
    }

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
