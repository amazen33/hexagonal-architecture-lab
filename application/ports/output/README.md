# Output Ports

## Purpose
Output ports (driven ports) are interfaces that define how your application communicates with external systems. These are implemented by adapters.

## Key Interfaces

### LoadUserPort.java
```java
public interface LoadUserPort {
    Optional<User> loadUser(UserId userId);
    Optional<User> loadUserByEmail(String email);
}
public interface SaveUserPort {
    User save(User user);
    void delete(UserId userId);
}
public interface NotifyUserPort {
    void sendNotification(UserId userId, Notification notification);
}
```
