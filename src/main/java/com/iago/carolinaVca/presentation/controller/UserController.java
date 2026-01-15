package com.iago.carolinaVca.presentation.controller;

import com.iago.carolinaVca.application.FindAllUsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iago.carolinaVca.application.CreateUserUseCase;
import com.iago.carolinaVca.presentation.request.CreateUserRequest;
import com.iago.carolinaVca.presentation.response.UserResponse;
import com.iago.carolinaVca.application.FindUserByIdUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findByIdUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            FindAllUsersUseCase findAllUsersUseCase,
            FindUserByIdUseCase findByIdUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.findByIdUserUseCase = findByIdUserUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {
        try {
            UserResponse response = createUserUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(findAllUsersUseCase.execute());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{cdUser}")
    public ResponseEntity<?> findByIdUser(@PathVariable Integer cdUser) {
        try {
            return ResponseEntity.ok(findByIdUserUseCase.execute(cdUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
