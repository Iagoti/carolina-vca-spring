package com.iago.carolinaVca.domain;

public class User {
    private Integer cdUser;
    private String nmUser;
    private String password;
    private Integer role;

    public User(Integer cdUser, String nmUser, String password) {
        this.cdUser = cdUser;
        this.nmUser = nmUser;
        this.password = password;
        this.validators();
    }

    public Integer getCdUser() {
        return cdUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return role;
    }
    
    private void validators() {    
        if (nmUser == null) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        if (password == null) {
            throw new IllegalArgumentException("A senha é obrigatória");
        }
        if (role == null) {
            throw new IllegalArgumentException("O cargo é obrigatório");
        }
    }
}
