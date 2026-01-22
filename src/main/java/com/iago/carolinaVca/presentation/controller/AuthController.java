package com.iago.carolinaVca.presentation.controller;

import com.iago.carolinaVca.application.auth.AuthenticateUserUseCase;
import com.iago.carolinaVca.domain.auth.dto.LoginRequestDTO;
import com.iago.carolinaVca.domain.auth.dto.LoginResponseDTO;
import com.iago.carolinaVca.domain.auth.exception.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticateUserUseCase useCase;

    public AuthController(AuthenticateUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        return ResponseEntity.ok(useCase.execute(body));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuth(AuthException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}

