package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.User;
import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.domain.vos.Name;
import com.iago.carolinaVca.presentation.mapper.UserPresentationMapper;
import com.iago.carolinaVca.presentation.request.CreateUserRequest;
import com.iago.carolinaVca.presentation.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Use Case: CreateUserUseCase")
class CreateUserUseCaseTest {

    @Mock
    private IUserRepository usuarioRepository;

    @Mock
    private UserPresentationMapper userPresentationMapper;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private User validUser;
    private CreateUserRequest validRequest;

    @BeforeEach
    void setUp() {
        Name name = new Name("João Silva");
        validUser = new User(1, name, "senha123", UserRoleEnum.ADMIN);

        validRequest = new CreateUserRequest();
        validRequest.setNmUser("João Silva");
        validRequest.setPassword("senha123");
        validRequest.setRole(UserRoleEnum.ADMIN.getCodigo());
    }

    @Test
    @DisplayName("Deve criar um usuário com sucesso")
    void shouldCreateUserSuccessfully() {
        User savedUser = new User(1, new Name("João Silva"), "senha123", UserRoleEnum.ADMIN);
        UserResponse savedResponse = new UserResponse(1, "João Silva", UserRoleEnum.ADMIN.name());

        when(userPresentationMapper.toDomain(any(CreateUserRequest.class))).thenReturn(validUser);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);
        when(userPresentationMapper.toResponse(savedUser)).thenReturn(savedResponse);

        UserResponse result = createUserUseCase.execute(validRequest);

        assertThat(result).isNotNull();
        assertThat(result.getCdUser()).isEqualTo(1);
        assertThat(result.getNmUser()).isEqualTo("João Silva");
        assertThat(result.getRole()).isEqualTo(UserRoleEnum.ADMIN.name());
        verify(usuarioRepository, times(1)).save(validUser);
    }

    @Test
    @DisplayName("Deve chamar o método save do repositório com o usuário correto")
    void shouldCallRepositorySaveWithCorrectUser() {
        User savedUser = new User(1, new Name("João Silva"), "senha123", UserRoleEnum.ADMIN);
        UserResponse savedResponse = new UserResponse(1, "João Silva", UserRoleEnum.ADMIN.name());

        when(userPresentationMapper.toDomain(any(CreateUserRequest.class))).thenReturn(validUser);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);
        when(userPresentationMapper.toResponse(savedUser)).thenReturn(savedResponse);

        createUserUseCase.execute(validRequest);

        verify(usuarioRepository, times(1)).save(validUser);
        verify(usuarioRepository, never()).delete(any(User.class));
        verify(usuarioRepository, never()).findByCdUser(anyInt());
    }

    @Test
    @DisplayName("Deve retornar o usuário salvo pelo repositório")
    void shouldReturnSavedUserFromRepository() {
        User savedUser = new User(2, new Name("Maria Santos"), "senha456", UserRoleEnum.EDITOR);
        UserResponse savedResponse = new UserResponse(2, "Maria Santos", UserRoleEnum.EDITOR.name());

        when(userPresentationMapper.toDomain(any(CreateUserRequest.class))).thenReturn(validUser);
        when(usuarioRepository.save(any(User.class))).thenReturn(savedUser);
        when(userPresentationMapper.toResponse(savedUser)).thenReturn(savedResponse);

        UserResponse result = createUserUseCase.execute(validRequest);

        assertThat(result).isSameAs(savedResponse);
        assertThat(result.getCdUser()).isEqualTo(2);
        assertThat(result.getNmUser()).isEqualTo("Maria Santos");
        assertThat(result.getRole()).isEqualTo(UserRoleEnum.EDITOR.name());
    }
}
