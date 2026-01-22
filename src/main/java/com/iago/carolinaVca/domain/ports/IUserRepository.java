package com.iago.carolinaVca.domain.ports;

import java.util.List;
import java.util.Optional;
import com.iago.carolinaVca.domain.model.User;

public interface IUserRepository {
    Optional<User> findByCdUser(Integer cdUser);
    User save(User user);
    void delete(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    Optional<User> findById(Integer cdUser);
}
