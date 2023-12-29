package com.example.clientapp.manager;

import com.example.clientapp.model.Order;
import com.example.clientapp.model.Smember;

public class OrderSessionManager {
    private static OrderSessionManager instance;
    private Order order;

    private OrderSessionManager() {}

    public static OrderSessionManager getInstance() {
        if(instance == null) {
            instance = new OrderSessionManager();
        }
        return instance;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
