package com.Revature.dtos.requests;

public class NewUpdateItemRequest {
    private String id;
    private String name;
    private int value;
    private int grade;
    private String store_id;
    private int quantity;

    public NewUpdateItemRequest() {
    }
    
    public NewUpdateItemRequest(String id, String name, int grade, int value, int quantity, String store_id) {
        this.name = name;
        this.value = value;
        this.grade = grade;
        this.quantity = quantity;
        this.store_id = store_id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStore_id() {
        return this.store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
