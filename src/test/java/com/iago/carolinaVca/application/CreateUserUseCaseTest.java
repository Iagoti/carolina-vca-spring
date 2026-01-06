package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.User;
import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.domain.vos.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use Case: CreateUserUseCase")
class CreateUserUseCaseTest {

    @Mock
    private IUserRepository usuarioRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private User validUser;

    @BeforeEach
    void setUp() {
        Name name = new Name("João Silva");
        validUser = new User(1, name, "senha123", UserRoleEnum.ADMIN);
    }

    @Test
    @DisplayName("Deve criar um usuário com sucesso")
    void shouldCreateUserSuccessfully() {
        // Arrange
        User savedUser = new User(1, new Name("João Silva"), "senha123", UserRoleEnum.ADMIN);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = createUserUseCase.execute(validUser);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCdUser()).isEqualTo(1);
        assertThat(result.getNmUser()).isEqualTo(new Name("João Silva"));
        assertThat(result.getRole()).isEqualTo(UserRoleEnum.ADMIN);
        verify(usuarioRepository, times(1)).save(validUser);
    }

    @Test
    @DisplayName("Deve chamar o método save do repositório com o usuário correto")
    void shouldCallRepositorySaveWithCorrectUser() {
        // Arrange
        User savedUser = new User(1, new Name("João Silva"), "senha123", UserRoleEnum.ADMIN);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        createUserUseCase.execute(validUser);

        // Assert
        verify(usuarioRepository, times(1)).save(validUser);
        verify(usuarioRepository, never()).delete(any(User.class));
        verify(usuarioRepository, never()).findByCdUser(anyInt());
    }

    @Test
    @DisplayName("Deve retornar o usuário salvo pelo repositório")
    void shouldReturnSavedUserFromRepository() {
        // Arrange
        User savedUser = new User(2, new Name("Maria Santos"), "senha456", UserRoleEnum.EDITOR);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = createUserUseCase.execute(validUser);

        // Assert
        assertThat(result).isSameAs(savedUser);
        assertThat(result.getCdUser()).isEqualTo(2);
        assertThat(result.getNmUser()).isEqualTo(new Name("Maria Santos"));
        assertThat(result.getRole()).isEqualTo(UserRoleEnum.EDITOR);
    }
}

