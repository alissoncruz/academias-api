package com.core.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiCustomizer {

    @Bean
    public OpenAPI customerOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                .info(new Info()
                        .title("API CT Ferro")
                        .version("1.0"));
    }

    @Bean
    public OpenApiCustomiser customOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> operation
                .addSecurityItem(new SecurityRequirement().addList("Bearer "))));
    }
}
