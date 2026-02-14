package com.example.adapters.inbound.rest;

import com.example.application.ports.input.CreateUserUseCase;
import com.example.application.ports.input.GetUserUseCase;
import com.example.domain.model.User;
import com.example.domain.model.User.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    
    public UserController(CreateUserUseCase createUserUseCase, GetUserUseCase getUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserUseCase.CreateUserCommand command = 
            new CreateUserUseCase.CreateUserCommand(request.email(), request.name());
        User user = createUserUseCase.createUser(command);
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User user = getUserUseCase.getUser(UserId.fromString(id));
        return ResponseEntity.ok(UserResponse.fromDomain(user));
    }
    
    record CreateUserRequest(String email, String name) {}
    
    record UserResponse(String id, String email, String name, String status) {
        static UserResponse fromDomain(User user) {
            // This would normally use a mapper
            return new UserResponse("id", "email", "name", "status");
        }
    }
}
