package com.Revature.dtos.requests;

public class NewOrderRequest {
    private String cartID;

    public NewOrderRequest() {
    }

    public NewOrderRequest(String cartID) {
        this.cartID = cartID;
    }

    public String getCartId() {
        return this.cartID;
    }

    public void setCartID(String cartId) {
        this.cartID = cartId;
    }

}
