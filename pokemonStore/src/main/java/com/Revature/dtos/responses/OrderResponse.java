package com.Revature.dtos.responses;

import java.util.ArrayList;
import java.util.List;

import com.Revature.models.Item;
import com.Revature.models.OrderItem;
// cost
// [] items {item {id, name, cost, grade, quantity, store_id}}
// order_date
// status
public class OrderResponse {
    private String order_date;
    private int cost;
    private String status;
    private List<OrderItem> items = new ArrayList();

    public OrderResponse() {
    }

    public OrderResponse(String order_date, int cost, String status, List<OrderItem> items) {
        this.order_date = order_date;
        this.cost = cost;
        this.status = status;
        this.items = items;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

}
