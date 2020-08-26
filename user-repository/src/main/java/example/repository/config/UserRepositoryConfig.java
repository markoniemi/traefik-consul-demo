package example.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.repository.DatabaseInitializer;

@Configuration
public class UserRepositoryConfig {
    @Bean
    public DatabaseInitializer getDatabaseInitializer() {
        return new DatabaseInitializer();
    }
}
