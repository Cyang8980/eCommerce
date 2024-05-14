package com.Revature.dtos.requests;

public class NewItemRequest {
    private String name;
    private int value;
    private int grade;
    private String storeName;
    private int quantity;
    public NewItemRequest() {
    }
    
    public NewItemRequest(String name, int grade, int value, int quantity, String storeName) {
        this.name = name;
        this.value = value;
        this.grade = grade;
        this.quantity = quantity;
        this.storeName = storeName;
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

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}   
