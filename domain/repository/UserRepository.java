package com.example.domain.repository;

import com.example.domain.model.User;
import com.example.domain.model.User.UserId;
import com.example.domain.model.User.Email;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(Email email);
    User save(User user);
    void delete(UserId id);
    boolean existsByEmail(Email email);
}
