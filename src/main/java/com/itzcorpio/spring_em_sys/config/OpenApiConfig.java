package com.itzcorpio.spring_em_sys.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Management System API",
                description = "Doing CRUD operatrions securely",
                summary = "This API will Add, Delete, Read and Update employees.",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Pavithra Chamod",
                        email = "pavithra@qcomit.com"
                ),
                version = "V1"
        ),
        security = @SecurityRequirement(
                name = "auth"
        )
)
@SecurityScheme(
        name = "auth",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Authorize"
)
public class OpenApiConfig {
}
