package com.acme.meetyourroommate.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "meetyourroommateOpenApi")
    public OpenAPI meetyourroommateOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Meet Your Roommate Application API")
                        .description(
        "whith Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));
    }
}
