package com.iago.carolinaVca.presentation.response;

public class UserResponse {
    private Integer cdUser;
    private String nmUser;
    private String email;
    private String role;
    
    public UserResponse(Integer cdUser, String nmUser, String email, String role) {
        this.cdUser = cdUser;
        this.nmUser = nmUser;
        this.email = email;
        this.role = role;
    }

    public Integer getCdUser() {
        return cdUser;
    }

    public void setCdUser(Integer cdUser) {
        this.cdUser = cdUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
