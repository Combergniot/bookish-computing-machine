package com.combergniot.githubapi.infrastructure;

import static springfox.documentation.builders.PathSelectors.any;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Dokumentacja API. Aplikacja Swaggera znajduje się pod adresem: http://{Base URL}/swagger-ui.html
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {


    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("github-api")
                .description("Api do pobierania danych użytkowników github")
                .contact(new Contact("Combergniot", null, "marcin.wernerowicz@gmail.com"))
                .license(null)
                .licenseUrl(null)
                .version("0.0.1")
                .build();
    }
}
