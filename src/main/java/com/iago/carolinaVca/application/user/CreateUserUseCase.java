package com.iago.carolinaVca.application.user;

import com.iago.carolinaVca.domain.exceptions.UserException;
import com.iago.carolinaVca.domain.model.User;
import com.iago.carolinaVca.domain.ports.IPasswordHasher;
import com.iago.carolinaVca.domain.ports.IUserRepository;
import com.iago.carolinaVca.presentation.mapper.UserPresentationMapper;
import com.iago.carolinaVca.presentation.request.CreateUserRequest;
import com.iago.carolinaVca.presentation.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {

    private final IUserRepository usuarioRepository;
    private final UserPresentationMapper userPresentationMapper;
    private final IPasswordHasher passwordHasher;

    public CreateUserUseCase(
            IUserRepository usuarioRepository,
            UserPresentationMapper userPresentationMapper,
            IPasswordHasher passwordHasher
    ) {
        this.usuarioRepository = usuarioRepository;
        this.userPresentationMapper = userPresentationMapper;
        this.passwordHasher = passwordHasher;
    }

    public UserResponse execute(CreateUserRequest request) {

        User userRequest = userPresentationMapper.toDomain(request);

        usuarioRepository.findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new UserException("E-mail já cadastrado no sistema.");
        });

        // 🔐 CRIPTOGRAFA A SENHA ANTES DE SALVAR
        User userWithHashedPassword = new User(
                userRequest.getCdUser(),
                userRequest.getNmUser(),
                passwordHasher.hash(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getRole()
        );

        User newUser = usuarioRepository.save(userWithHashedPassword);
        return userPresentationMapper.toResponse(newUser);
    }
}

