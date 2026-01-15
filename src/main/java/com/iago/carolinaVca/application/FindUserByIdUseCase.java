package com.iago.carolinaVca.application;

import com.iago.carolinaVca.domain.model.User;
import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.presentation.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdUseCase {

    private final IUserRepository userRepository;

    public FindUserByIdUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(Integer cdUser) throws Exception {
        User user = userRepository.findById(cdUser)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));

        return new UserResponse(
                user.getCdUser(),
                user.getNmUser().getValue(),
                user.getEmail(),
                user.getRole().getDescricao()
        );
    }
}
