package com.dynatrace.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig{

        @Bean
        public OpenAPI openAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Dynatrace exchange api")
                            .description("Recruitment application")
                            .version("1.0.0")
                            .contact(new Contact()
                                    .email("szymkowiak.bartosz@outlook.com")
                                    .name("Bartosz Szymkowiak")
                                    .url("https://github.com/bszymkowiak")));
        }
}
