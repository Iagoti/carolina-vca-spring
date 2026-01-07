package com.iago.carolinaVca.presentation.request;

public class CreateUserRequest {
    private String nmUser;
    private String password;
    private Integer role;

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
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }
}

