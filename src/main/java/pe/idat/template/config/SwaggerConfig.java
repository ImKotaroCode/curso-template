package pe.idat.template.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mi Proyecto Spring Boot API")
                        .description("API REST para gestión de usuarios y productos")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@empresa.com")
                                .url("https://empresa.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
