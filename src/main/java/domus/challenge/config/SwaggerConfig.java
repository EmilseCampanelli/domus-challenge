package domus.challenge.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI domusChallengeOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Domus Back-End Challenge API")
                        .description("API that returns directors who have directed more movies than a given threshold.")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Diseño Tecnico")
                        .url("https://drive.google.com/file/d/1rBXSzvAW7LyKbooE-sGtDzeZp5al-uXT/view"))
                .addTagsItem(new Tag()
                        .name("Directors")
                        .description("Operations related to movie directors"));

    }
}
