package com.iago.carolinaVca.domain.ports;

public interface IPasswordHasher {
    boolean matches(String rawPassword, String hashedPassword);
    String hash(String rawPassword);
}
