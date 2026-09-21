package com.example.rest_api.config;

import com.example.rest_api.adapter.out.repository.UserRepository;
import com.example.rest_api.domain.user.User;
import com.example.rest_api.domain.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private final UserRepository repository;

    public DataInitializer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        String defaultLogin = "devchicala";
        if (this.repository.findByLogin(defaultLogin) == null) {
            String rawPassword = "123456789";
            String encoded = new BCryptPasswordEncoder().encode(rawPassword);
            User admin = new User(defaultLogin, encoded, UserRole.ADMIN);
            this.repository.save(admin);
        }
    }
}
