package br.com.carlos.clothes_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    
    @Bean
    public PasswordEncoder passwordEnCoder() {
        return new BCryptPasswordEncoder();
    }
}
