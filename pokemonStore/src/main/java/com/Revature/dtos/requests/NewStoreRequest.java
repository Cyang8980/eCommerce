package com.Revature.dtos.requests;

public class NewStoreRequest {
    private String name;

    public NewStoreRequest() {
        
    }
    public NewStoreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
