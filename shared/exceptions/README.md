# Custom Exceptions

## Purpose
Custom exception classes for handling domain errors and business rule violations.

## Key Exceptions

### DomainException.java
```java
public class DomainException extends RuntimeException {
    private final String code;
    private final String message;
    
    public DomainException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

public class BusinessRuleException extends DomainException {
    public BusinessRuleException(String message) {
        super("BUSINESS_RULE_VIOLATION", message);
    }
}
```
