package com.iago.carolinaVca.domain;

import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.exceptions.UserException;
import com.iago.carolinaVca.domain.vos.Name;

public class User {
    private Integer cdUser;
    private Name nmUser;
    private String password;
    private UserRoleEnum role;

    public User(Integer cdUser, Name nmUser, String password, UserRoleEnum role) {
        this.cdUser = cdUser;
        this.nmUser = nmUser;
        this.password = password;
        this.role = role;
        this.validators();
    }

    public Integer getCdUser() {
        return cdUser;
    }

    public Name getNmUser() {
        return nmUser;
    }

    public String getPassword() {
        return password;
    }

    public UserRoleEnum getRole() {
        return role;
    }
    
    private void validators() {    
        if (nmUser == null) {
            throw new UserException("Nome do usuário é obrigatório");
        }
        if (password == null) {
            throw new UserException("A senha é obrigatória");
        }
        if (role == null) {
            throw new UserException("O cargo é obrigatório");
        }
    }
}
