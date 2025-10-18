package dev.matheuslf.desafio.inscritos.configuration.docs;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Management tasks and projects API ")
                        .version("1.0.0")
                        .description("Chalange with subscribers of the YouTube chanel -> @matheuslf "));
    }
}
