package com.iago.carolinaVca.presentation.mapper;

import org.springframework.stereotype.Component;

import com.iago.carolinaVca.domain.User;
import com.iago.carolinaVca.domain.enums.UserRoleEnum;
import com.iago.carolinaVca.domain.vos.Name;
import com.iago.carolinaVca.presentation.request.CreateUserRequest;
import com.iago.carolinaVca.presentation.response.UserResponse;

@Component
public class UserPresentationMapper {

    public User toDomain(CreateUserRequest request) {
        return new User(
                null,
                new Name(request.getNmUser()),
                request.getPassword(),
                UserRoleEnum.fromCodigo(request.getRole())
        );
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getCdUser(),
                user.getNmUser().getValue(),
                user.getRole().name()
        );
    }
}

