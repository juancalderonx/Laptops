package Package.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket getBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //Aquí estoy declarando la información de la documentación de los datos de la API
    private ApiInfo apiInfo(){
        return new ApiInfo("Laptops API REST",
                "Mi primer API REST gestionando laptops con un CRUD usando Spring Boot como Backend.",
                "1.0",
                "https://www.google.com",
                new Contact("Juan", "https://github.com/juancalderonx", "juancalderonbrs@gmail.com"),
                "Libre completa",
                "https://google.com",
                Collections.emptyList()); //Colección de datos vacía, ya que no necesitamos documentar mucho, es un ejercicio práctico.
    }

}
