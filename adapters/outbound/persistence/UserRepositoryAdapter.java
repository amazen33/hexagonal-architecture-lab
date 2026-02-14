package com.example.adapters.outbound.persistence;

import com.example.application.ports.output.LoadUserPort;
import com.example.application.ports.output.SaveUserPort;
import com.example.domain.model.User;
import com.example.domain.model.User.UserId;
import com.example.domain.model.User.Email;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements LoadUserPort, SaveUserPort {
    
    private final SpringDataUserRepository jpaRepository;
    private final UserEntityMapper mapper;
    
    public UserRepositoryAdapter(SpringDataUserRepository jpaRepository, UserEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
    
    @Override
    public Optional<User> loadUser(UserId userId) {
        return jpaRepository.findById(userId.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public Optional<User> loadUserByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public void delete(UserId userId) {
        jpaRepository.deleteById(userId.getValue());
    }
}
