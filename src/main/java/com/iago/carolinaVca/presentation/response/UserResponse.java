package com.iago.carolinaVca.presentation.response;

public class UserResponse {
    private Integer cdUser;
    private String nmUser;
    private String role;
    
    public UserResponse(Integer cdUser, String nmUser, String role) {
        this.cdUser = cdUser;
        this.nmUser = nmUser;
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
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
