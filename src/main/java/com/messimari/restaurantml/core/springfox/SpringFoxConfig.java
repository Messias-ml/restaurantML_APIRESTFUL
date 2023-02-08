package com.messimari.restaurantml.core.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("City", "City of state"));
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("RestaurantML")
                .description("Api de restaurant")
                .version("1")
                .contact(new Contact("Messias", "https://www.linkedin.com/in/messias-dev-silva/","messias.silva_dev@hotmail.com"))
                .build();
    }
}
