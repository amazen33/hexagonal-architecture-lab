# Configuration Layer

## Purpose
The configuration layer handles application setup, bean definitions, and external configuration integration.

## Structure

### DatabaseConfig.java
Configures database connections, JPA settings, and transaction management.

### SecurityConfig.java
Handles authentication, authorization, and security policies.

### MessagingConfig.java
Configures message brokers, queues, and event handling.

## Usage

### Database Configuration
```java
@Configuration
@EnableJpaRepositories(basePackages = "com.example.adapters.outbound.persistence")
public class DatabaseConfig {
    
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("${spring.datasource.url}")
            .username("${spring.datasource.username}")
            .password("${spring.datasource.password}")
            .build();
    }
}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/users/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .build();
    }
}

@Configuration
@EnableKafka
public class MessagingConfig {
    
    @Bean
    public ConsumerFactory<String, UserEventMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
```
