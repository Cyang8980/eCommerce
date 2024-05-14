package com.Revature.models;

import java.util.UUID;

public class Cart {
    private String id;
    private String belongsTo;
    
    public Cart() {

    }

    public Cart(String id) {
        this.id = UUID.randomUUID().toString();
        this.belongsTo = id;
    }

    public String getCart_id() {
        return this.id;
    }

    public void setCart_id(String id) {
        this.id = id;
    }

    public String getBelongsTo() {
        return this.belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }
}
