package com.Revature.dtos.requests;

public class NewDeleteItemRequest {
    private String ID;

    public NewDeleteItemRequest() {
    }

    public NewDeleteItemRequest(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
