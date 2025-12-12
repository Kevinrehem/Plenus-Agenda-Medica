package com.jacaboy.plenus_agenda_medica.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Minha API", version = "v1"),
        security = @SecurityRequirement(name = "basicAuth") // Aplica a segurança globalmente
)
@SecurityScheme(
        name = "basicAuth", // O nome deve bater com o security acima
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfig {
    // Nenhuma lógica necessária aqui dentro, as anotações fazem o trabalho
}