package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.User;
import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.presentation.mapper.UserPresentationMapper;
import com.iago.carolinaVca.presentation.request.CreateUserRequest;
import com.iago.carolinaVca.presentation.response.UserResponse;

public class CreateUserUseCase {

    private final IUserRepository usuarioRepository;
    private final UserPresentationMapper userPresentationMapper;

    public CreateUserUseCase(
            IUserRepository usuarioRepository,
            UserPresentationMapper userPresentationMapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.userPresentationMapper = userPresentationMapper;
    }

    public UserResponse execute(CreateUserRequest request) {
        User userRequest = userPresentationMapper.toDomain(request);
        User newUser = usuarioRepository.save(userRequest);
        UserResponse userResponse = userPresentationMapper.toResponse(newUser);
        return userResponse;
    }
}
