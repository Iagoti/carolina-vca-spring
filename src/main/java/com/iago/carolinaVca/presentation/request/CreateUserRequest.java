package com.iago.carolinaVca.presentation.request;

public class CreateUserRequest {
    private String nmUser;
    private String password;
    private String role;

    public String getNmUser() {
        return nmUser;
    }
    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}

