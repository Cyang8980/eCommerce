package com.Revature.models;

import java.util.UUID;

public class Role {
    private String id;
    private String name; 
    public Role() {

    }
    public Role (String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
    
    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
