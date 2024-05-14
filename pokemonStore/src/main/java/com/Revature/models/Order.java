package com.Revature.models;


public class Order {
    String order_id;
    String user_id;
    String order_date;
    int cost;
    String status;

    public Order() {
    }

    public Order(String order_id, String user_id, String order_date, int cost, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.cost = cost;
        this.status = status;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return this.order_date;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
