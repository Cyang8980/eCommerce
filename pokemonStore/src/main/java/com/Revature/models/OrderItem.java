package com.Revature.models;

import java.util.UUID;

public class OrderItem {
    private String orderItemid;
    private String item_id;
    private int quantity;
    private int cost;

    public OrderItem() {

    }

    public OrderItem(String item_id, int quantity, int cost) {
        this.orderItemid = UUID.randomUUID().toString();
        this.item_id = item_id;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getOrder_id() {
        return this.orderItemid;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrder_id(String order_id) {
        this.orderItemid = order_id;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public String toString() {
        return (this.item_id);
    }
}
