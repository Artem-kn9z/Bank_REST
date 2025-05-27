package com.example.bankcards.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(
        title = "Effective Mobile Bank API",
        description = "API for the simple Bank", version = "1.0.0",
        contact = @Contact(
                name = "Artem Chernjakov",
                email = "artem4536443@gmail.com"
        )
))
public class SwaggerConfig {
}
