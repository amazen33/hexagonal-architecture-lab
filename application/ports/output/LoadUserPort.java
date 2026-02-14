package com.example.application.ports.output;

import com.example.domain.model.User;
import com.example.domain.model.User.UserId;
import com.example.domain.model.User.Email;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUser(UserId userId);
    Optional<User> loadUserByEmail(Email email);
}
