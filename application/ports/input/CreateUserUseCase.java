package com.example.application.ports.input;

import com.example.domain.model.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand command);
    
    record CreateUserCommand(String email, String name) {}
}
