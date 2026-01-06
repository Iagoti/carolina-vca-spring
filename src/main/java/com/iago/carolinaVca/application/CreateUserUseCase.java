package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.User;
import com.iago.carolinaVca.domain.repositories.IUserRepository;

public class CreateUserUseCase {
    private final IUserRepository usuarioRepository;

    public CreateUserUseCase(IUserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public User execute(User user) {
        return usuarioRepository.save(user);
    }
}
