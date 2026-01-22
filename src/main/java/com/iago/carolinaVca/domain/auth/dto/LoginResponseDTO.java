package com.iago.carolinaVca.domain.auth.dto;

public record LoginResponseDTO(
        String token,
        Integer cdUser,
        String nmUser,
        String email,
        Integer role
) {}
