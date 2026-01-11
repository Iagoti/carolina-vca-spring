package com.iago.carolinaVca.domain;

import com.iago.carolinaVca.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.exceptions.UserException;
import com.iago.carolinaVca.domain.vos.Name;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Entity: User")
class UserTest {

    @Test
    @DisplayName("Deve criar um usuário válido com papel de ADMIN")
    void shouldCreateValidAdminUser() {
        Name name = new Name("Ana Maria");
        Integer cdUser = 1;
        String password = "senha123";
        UserRoleEnum role = UserRoleEnum.ADMIN;
        User user = new User(cdUser, name, password, role);

        assertThat(user.getCdUser()).isEqualTo(cdUser);
        assertThat(user.getNmUser()).isEqualTo(new Name("Ana Maria"));
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getRole()).isEqualTo(role);
        assertThat(user.getRole().getCodigo()).isEqualTo(1);
    }

    @ParameterizedTest
    @EnumSource(UserRoleEnum.class)
    @DisplayName("Deve criar um usuário válido com diferentes roles")
    void shouldCreateValidUserWithDifferentRoles(UserRoleEnum role) {
        Name name = new Name("João Silva");
        Integer cdUser = 1;
        String password = "senha123";
        User user = new User(cdUser, name, password, role);

        assertThat(user.getCdUser()).isEqualTo(cdUser);
        assertThat(user.getNmUser()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getRole()).isEqualTo(role);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "Ab"})
    @DisplayName("Deve lançar exceção quando tentar criar usuário com nome inválido")
    void shouldThrowExceptionForInvalidNames(String invalidName) {
        assertThatThrownBy(() -> {
            Name name = new Name(invalidName);
            new User(1, name, "senha123", UserRoleEnum.ADMIN);
        })
            .isInstanceOf(UserException.class);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o role for nulo na entidade")
    void shouldThrowExceptionWhenRoleIsNull() {
        Name validName = new Name("João Silva");

        assertThatThrownBy(() -> new User(1, validName, "senha123", null))
            .isInstanceOf(UserException.class)
            .hasMessage("O cargo é obrigatório");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome do usuário for nulo na entidade")
    void shouldThrowExceptionWhenUserNameIsNull() {
        assertThatThrownBy(() -> new User(1, null, "senha123", UserRoleEnum.EDITOR))
            .isInstanceOf(UserException.class)
            .hasMessage("Nome do usuário é obrigatório");
    }

    @Test
    @DisplayName("Deve lançar exceção quando a senha for nula")
    void shouldThrowExceptionWhenPasswordIsNull() {
        Name validName = new Name("João Silva");

        assertThatThrownBy(() -> new User(1, validName, null, UserRoleEnum.ADMIN))
            .isInstanceOf(UserException.class)
            .hasMessage("A senha é obrigatória");
    }

    @Test
    @DisplayName("Deve aceitar senha vazia (validação apenas para null)")
    void shouldAcceptEmptyPassword() {
        Name validName = new Name("João Silva");
        String emptyPassword = "";
        User user = new User(1, validName, emptyPassword, UserRoleEnum.ADMIN);

        assertThat(user.getPassword()).isEqualTo(emptyPassword);
    }

    @Test
    @DisplayName("Deve aceitar cdUser nulo")
    void shouldAcceptNullCdUser() {
        Name validName = new Name("João Silva");
        User user = new User(null, validName, "senha123", UserRoleEnum.ADMIN);

        assertThat(user.getCdUser()).isNull();
        assertThat(user.getNmUser()).isEqualTo(validName);
        assertThat(user.getPassword()).isEqualTo("senha123");
        assertThat(user.getRole()).isEqualTo(UserRoleEnum.ADMIN);
    }
}