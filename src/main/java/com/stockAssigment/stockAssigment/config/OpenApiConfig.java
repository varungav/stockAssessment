package com.stockAssigment.stockAssigment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Varun Gavoor",
                        email = "varun.g8866@gmail.com"
                ),
                description = "OpenApi documentation for Spring Boot Project",
                title = "OpenApi specification - Varun Gavoor"
        ),
        servers = {
                @Server(
                        description = "The default access Link is as below",
                        url = "http://localhost:8080"
                )
        }
)


public class OpenApiConfig {
}
