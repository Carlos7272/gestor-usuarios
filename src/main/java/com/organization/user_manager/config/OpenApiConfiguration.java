package com.organization.user_manager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Manager API",
                version = "1.0",
                description = "Documentation for endpoints in User Manager")
)
public class OpenApiConfiguration {
}
