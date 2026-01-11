package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.presentation.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllUsersUseCase {

    private final IUserRepository userRepository;

    public FindAllUsersUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> execute() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getCdUser(),
                        user.getNmUser().getValue(),
                        user.getEmail(),
                        user.getRole().getDescricao()
                ))
                .toList();
    }
}
