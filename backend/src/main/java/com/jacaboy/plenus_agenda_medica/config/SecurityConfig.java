package com.jacaboy.plenus_agenda_medica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita CSRF (comum para APIs stateless)
                .csrf(csrf -> csrf.disable())
                // 2. Define a política de sessão como STATELESS (não guarda estado)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 3. Define as regras de autorização
                .authorizeHttpRequests(auth -> auth
                        // Liberação do Swagger/OpenAPI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Suas regras existentes
                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/patient/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/availability/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/professional/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/select-all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/procedure/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointment/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/appointment/select-all").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}