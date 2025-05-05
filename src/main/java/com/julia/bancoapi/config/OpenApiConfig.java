package com.julia.bancoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Bancária Simples")
                        .version("1.0.0")
                        .description("API REST para operações bancárias")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}
