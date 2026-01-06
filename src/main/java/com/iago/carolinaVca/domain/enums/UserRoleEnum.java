package com.iago.carolinaVca.domain.enums;

import com.iago.carolinaVca.domain.exceptions.UserException;

public enum UserRoleEnum {
    ADMIN(1, "Administrador"),
    EDITOR(2, "Editor"),
    VIEWER(3, "Visualizador");

    private final int codigo;
    private final String descricao;

    UserRoleEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static UserRoleEnum fromCodigo(Integer codigo) {
        if (codigo == null) {
            throw new UserException("Código do cargo não pode ser nulo");
        }
        for (UserRoleEnum role : values()) {
            if (role.getCodigo() == codigo) {
                return role;
            }
        }
        throw new UserException("Código de cargo inválido: " + codigo);
    }

    public static UserRoleEnum fromDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new UserException("Descrição do cargo não pode ser nula");
        }
        for (UserRoleEnum role : values()) {
            if (role.getDescricao().equalsIgnoreCase(descricao)) {
                return role;
            }
        }
        throw new UserException("Descrição de cargo inválida: " + descricao);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
