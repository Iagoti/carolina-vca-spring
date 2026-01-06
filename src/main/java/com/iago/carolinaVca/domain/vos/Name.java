package com.iago.carolinaVca.domain.vos;

import com.iago.carolinaVca.domain.exceptions.UserException;

public final class Name {
    private final String value;

    public Name(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new UserException("Nome do usuário é obrigatório");
        }
        if (value.length() < 3) {
            throw new UserException("Nome deve ter no mínimo 3 caracteres");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
    
    // Equals e HashCode são essenciais para Value Objects
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Name userName = (Name) object;
        return value.equals(userName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
