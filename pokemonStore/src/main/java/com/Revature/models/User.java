package com.Revature.models;

import java.util.UUID;

import com.Revature.dtos.requests.NewRegisterRequest;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String role_id;
    private String cart_id;
    private Role role;

    public User () {

    }

    public User (String[] data) {
        this.id = data[0];
        this.username = data[1];
        this.password = data[2];
        this.email = data[3];
        this.role_id = data[4];
        this.cart_id = data[5];
    }

    public User(String username, String password, String email) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.cart_id = new Cart().getCart_id();
    }

    public User (NewRegisterRequest req) {
        this.id = UUID.randomUUID().toString();
        this.username = req.getUsername();
        this.password = req.getPassword();
        this.email = req.getEmail();
    }

    public void setUsername(String usernmae) {
        this.username = usernmae;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole_id() {
        return this.role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public void setCartID(String cartID) {
        this.cart_id = cartID;
    }

    public String getCartID() {
        return this.cart_id;
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

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
