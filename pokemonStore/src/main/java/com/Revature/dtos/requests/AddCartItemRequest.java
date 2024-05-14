package com.Revature.dtos.requests;

public class AddCartItemRequest {
    private String cartId;
    private String itemId;
    private int quantity;

    public AddCartItemRequest() {
        
    }

    public AddCartItemRequest(String itemId, String cartId, int quantity) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }   

    public String getCartId() {
        return this.cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
}
