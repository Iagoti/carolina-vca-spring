package com.iago.carolinaVca.application.auth;

import com.iago.carolinaVca.domain.ports.IPasswordHasher;
import com.iago.carolinaVca.domain.ports.ITokenProvider;
import com.iago.carolinaVca.domain.ports.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(
            ITokenProvider tokenProvider,
            IUserRepository userRepository,
            IPasswordHasher passwordHasher
    ) {
        return new AuthenticateUserUseCase(tokenProvider, userRepository, passwordHasher);
    }
}

