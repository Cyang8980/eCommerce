package com.Revature.models;

import java.util.UUID;

public class Item {
    private String item_id;
    private String name;
    private int value;
    private int grade;
    private int quantity;
    private String store_id;
    public Item() {

    }

    public Item(String name, int value, int grade, int quantity, String store_id) {
        this.item_id = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
        this.grade = grade;
        this.quantity = quantity;
        this.store_id = store_id;
    }

    public Item(String name, int value) {
        this.item_id = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
    }


    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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
    public String toString() {
        return this.name;
    }
}
