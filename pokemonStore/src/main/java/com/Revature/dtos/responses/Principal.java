package com.Revature.dtos.responses;

import com.Revature.models.Role;
import com.Revature.models.User;

public class Principal {
    private String id;
    private String username;
    private Role role;


    public Principal() {
    }

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public Principal(String id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
