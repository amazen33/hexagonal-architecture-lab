package com.example.domain.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UserId id;
    private Email email;
    private Name name;
    private UserStatus status;
    
    private User(UserId id, Email email, Name name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = UserStatus.ACTIVE;
    }
    
    public static User create(String email, String name) {
        return new User(
            UserId.generate(),
            new Email(email),
            new Name(name)
        );
    }
    
    public void deactivate() {
        if (this.status == UserStatus.INACTIVE) {
            throw new IllegalStateException("User already inactive");
        }
        this.status = UserStatus.INACTIVE;
    }
    
    // Value Objects
    public static class UserId {
        private final String value;
        
        private UserId(String value) {
            this.value = value;
        }
        
        public static UserId generate() {
            return new UserId(UUID.randomUUID().toString());
        }
        
        public static UserId fromString(String value) {
            return new UserId(value);
        }
        
        public String getValue() { return value; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserId userId = (UserId) o;
            return Objects.equals(value, userId.value);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
    
    public static class Email {
        private final String value;
        
        public Email(String value) {
            if (!isValidEmail(value)) {
                throw new IllegalArgumentException("Invalid email format");
            }
            this.value = value;
        }
        
        private boolean isValidEmail(String email) {
            return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        }
        
        public String getValue() { return value; }
    }
    
    public static class Name {
        private final String value;
        
        public Name(String value) {
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            this.value = value;
        }
        
        public String getValue() { return value; }
    }
    
    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
