package com.miguelcastro.testing.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.disable()) // Habilitar CORS
        .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para pruebas
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Permitir todas las rutas

    return http.build();
  }
}
