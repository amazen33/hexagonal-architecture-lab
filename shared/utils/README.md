# Utilities

## Purpose
Helper classes and utility functions used across the application.

## Key Utilities

### ValidationUtils.java
```java
public final class ValidationUtils {
    private ValidationUtils() {}
    
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    public static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new BusinessRuleException(message);
        }
    }
}
```
