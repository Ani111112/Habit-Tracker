package org.habittracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**", "/actuator/**")) // Optional: disables CSRF for Eureka
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/eureka/**", "/actuator/**", "/").permitAll() // 👈 This is the key
                        .anyRequest().authenticated() // Or `.permitAll()` if you want to allow everything
                );

        return httpSecurity.build();
    }
}
