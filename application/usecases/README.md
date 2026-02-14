# Use Cases

## Purpose
Use cases orchestrate the domain layer to fulfill specific business requirements. They implement the input ports and coordinate between domain objects and output ports.

## Key Services

### CreateUserService.java
Implements user creation flow.

### GetUserService.java
Handles user retrieval.

### UpdateUserService.java
Manages user updates.

## Example
```java
@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final UserMapper mapper;
    
    @Override
    public User createUser(CreateUserCommand command) {
        // Business flow orchestration
        validateEmailNotExists(command.getEmail());
        User user = User.create(command.getEmail(), command.getName());
        return saveUserPort.save(user);
    }
    
    private void validateEmailNotExists(String email) {
        loadUserPort.loadUserByEmail(email)
            .ifPresent(user -> {
                throw new EmailAlreadyExistsException(email);
            });
    }
}
```
