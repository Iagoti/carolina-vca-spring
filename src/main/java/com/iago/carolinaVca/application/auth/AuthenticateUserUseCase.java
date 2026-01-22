package com.iago.carolinaVca.application.auth;

import com.iago.carolinaVca.domain.auth.dto.LoginRequestDTO;
import com.iago.carolinaVca.domain.auth.dto.LoginResponseDTO;
import com.iago.carolinaVca.domain.auth.exception.AuthException;
import com.iago.carolinaVca.domain.model.User;
import com.iago.carolinaVca.domain.ports.IPasswordHasher;
import com.iago.carolinaVca.domain.ports.ITokenProvider;
import com.iago.carolinaVca.domain.ports.IUserRepository;

public class AuthenticateUserUseCase {

    private final ITokenProvider tokenProvider;
    private final IUserRepository userRepository;
    private final IPasswordHasher passwordHasher;

    public AuthenticateUserUseCase(
            ITokenProvider tokenProvider,
            IUserRepository userRepository,
            IPasswordHasher passwordHasher
    ) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public LoginResponseDTO execute(LoginRequestDTO input) {
        if (input == null || input.email() == null || input.password() == null) {
            throw new AuthException("Email e senha são obrigatórios");
        }

        User user = userRepository.findByEmail(input.email())
                .orElseThrow(() -> new AuthException("Credenciais inválidas"));

        boolean ok = passwordHasher.matches(input.password(), user.getPassword());
        if (!ok) throw new AuthException("Credenciais inválidas");

        String token = tokenProvider.generate(user);

        return new LoginResponseDTO(
                token,
                user.getCdUser(),
                user.getNmUser() != null ? user.getNmUser().toString() : null,
                user.getEmail(),
                user.getRole() != null ? user.getRole().getCodigo() : null
        );
    }
}
