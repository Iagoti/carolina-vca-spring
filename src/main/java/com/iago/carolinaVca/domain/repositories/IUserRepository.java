package com.iago.carolinaVca.domain.repositories;

import java.util.Optional;
import com.iago.carolinaVca.domain.User;

public interface IUserRepository {
    Optional<User> findByCdUser(Integer cdUser);
    User save(User user);
    void delete(User user);
}
