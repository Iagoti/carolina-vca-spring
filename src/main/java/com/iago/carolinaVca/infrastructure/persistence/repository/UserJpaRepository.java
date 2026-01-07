package com.iago.carolinaVca.infrastructure.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iago.carolinaVca.infrastructure.persistence.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByCdUser(Integer cdUser);
    UserEntity save(UserEntity user);
    void delete(UserEntity user);
}
