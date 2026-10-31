package br.com.housebossinterno.houseboss.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {

        Contact contact = new Contact();
        contact.name("Gabriel");
        contact.email("gabrieloliveira0094@gamil.com");

        Info info = new Info();
        info.title("House Boss ");
        info.version("v1");
        info.description("Aplicação para gerenciamento de atendimentos");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
