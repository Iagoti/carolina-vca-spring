package com.iago.carolinaVca.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.iago.carolinaVca.domain.model.User;
import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.vos.Name;
import com.iago.carolinaVca.infrastructure.persistence.entity.UserEntity;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setCdUser(user.getCdUser());
        entity.setNmUser(user.getNmUser().getValue());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole().getCodigo());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getCdUser(),
                new Name(entity.getNmUser()),
                entity.getPassword(),
                entity.getEmail(),
                UserRoleEnum.fromCodigo(entity.getRole())
        );
    }
}

