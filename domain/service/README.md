# Domain Services

## Purpose
Domain services contain business logic that doesn't naturally fit within a single entity.

## Key Services

### UserService.java
Handles user-related business operations.

### OrderService.java
Manages order processing and validation.

### ProductService.java
Handles product catalog operations.

## Usage
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    public User registerUser(RegistrationData data) {
        // Business validation
        validateEmail(data.getEmail());
        validatePassword(data.getPassword());
        
        User user = User.create(data);
        return userRepository.save(user);
    }
}
```
