package com.iago.carolinaVca.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.iago.carolinaVca.domain.model.User;
import com.iago.carolinaVca.domain.repositories.IUserRepository;
import com.iago.carolinaVca.infrastructure.persistence.entity.UserEntity;
import com.iago.carolinaVca.infrastructure.persistence.mapper.UserPersistenceMapper;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    public UserRepositoryImpl(UserJpaRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByCdUser(Integer cdUser) {
        return jpaRepository.findById(cdUser)
                .map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(User user) {
        jpaRepository.deleteById(user.getCdUser());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}

