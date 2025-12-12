package com.jacaboy.plenus_agenda_medica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

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
                        // LIBERA O SWAGGER E OS RECURSOS DELE
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // LIBERA REQUESTS AUTENTICADAS
                        .anyRequest().authenticated()
                )
                // 4. Habilita Auth button
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /* @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}