package com.Revature.dtos.requests;

public class GetCartRequest {
    private String user_id;
    private String  cart_id;

    public GetCartRequest() {
    }

    public GetCartRequest(String user_id, String cart_id) {
        this.user_id = user_id;
        this.cart_id = cart_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
}
