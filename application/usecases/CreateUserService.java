package com.example.application.usecases;

import com.example.application.ports.input.CreateUserUseCase;
import com.example.application.ports.output.LoadUserPort;
import com.example.application.ports.output.SaveUserPort;
import com.example.domain.model.User;
import com.example.domain.model.User.Email;
import org.springframework.stereotype.Component;

@Component
public class CreateUserService implements CreateUserUseCase {
    
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    
    public CreateUserService(LoadUserPort loadUserPort, SaveUserPort saveUserPort) {
        this.loadUserPort = loadUserPort;
        this.saveUserPort = saveUserPort;
    }
    
    @Override
    public User createUser(CreateUserCommand command) {
        // Check if user already exists
        Email email = new Email(command.email());
        loadUserPort.loadUserByEmail(email)
            .ifPresent(user -> {
                throw new RuntimeException("Email already registered: " + command.email());
            });
        
        // Create and save user
        User user = User.create(command.email(), command.name());
        return saveUserPort.save(user);
    }
}
