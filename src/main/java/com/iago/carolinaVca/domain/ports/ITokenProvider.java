package com.iago.carolinaVca.domain.ports;

import com.iago.carolinaVca.domain.model.User;

public interface ITokenProvider {
    String generate(User user);
}
