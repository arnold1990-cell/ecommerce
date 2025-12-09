package com.ecommerce.ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "E-Commerce API",
                version = "1.0",
                description = "API documentation for E-Commerce Application",
                contact = @Contact(
                        name = "Arnold Madamombe",
                        email = "arnoldmadaz@gmail.com"
                ),
                license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")
        ),
        servers = {
                @Server(description = "Local", url = "http://localhost:8080"),
                @Server(description = "Production", url = "https://api.ecommerce.com")
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authentication using Bearer token"
)
public class OpenApiConfig {}
