package com.Revature.dtos.requests;

public class updateQuantityRequest {
    private String cartId;
    private String itemId;
    private String addOrSubtract;


    public updateQuantityRequest() {
    }

    public updateQuantityRequest(String cartId, String itemId, String addOrSubtract) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.addOrSubtract = addOrSubtract;
    }

    public String getCartId() {
        return this.cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAddOrSubtract() {
        return this.addOrSubtract;
    }

    public void setAddOrSubtract(String addOrSubtract) {
        this.addOrSubtract = addOrSubtract;
    }

}
