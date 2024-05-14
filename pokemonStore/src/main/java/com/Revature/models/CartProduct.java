package com.Revature.models;

public class CartProduct {
    private String item_id;
    private String cart_id;
    private int quantity;

    public CartProduct() {
    }

    public CartProduct(String item_id, String cart_id, int quantity) {
        this.item_id = item_id;
        this.cart_id = cart_id;
        this.quantity = quantity;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
    

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return this.item_id;
    }

}
