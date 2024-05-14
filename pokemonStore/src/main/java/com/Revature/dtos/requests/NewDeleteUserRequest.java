package com.Revature.dtos.requests;

public class NewDeleteUserRequest {
    private String id;
    private String cart_id;

    public NewDeleteUserRequest() {

    }
    
    public NewDeleteUserRequest(String id, String cart_id) {
        this.id = id;
        this.cart_id = cart_id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

}
