package com.Revature.dtos.requests;

public class NewLoginRequest {
    private String username;
    private String password;

    public NewLoginRequest() {

    }
    public NewLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
