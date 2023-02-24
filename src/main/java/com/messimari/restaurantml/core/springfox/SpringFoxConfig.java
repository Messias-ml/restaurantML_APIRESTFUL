package com.messimari.restaurantml.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.messimari.restaurantml.api.handler.FieldValidation;
import com.messimari.restaurantml.api.handler.Problem;
import com.messimari.restaurantml.api.handler.ProblemWithField;
import com.messimari.restaurantml.domain.model.StatusDemand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        TypeResolver typeResolver = new TypeResolver();


        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.messimari.restaurantml.api.controller"))
                .build()
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPutResponseMessages())
                .globalResponses(HttpMethod.PATCH, globalPatchResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .additionalModels(typeResolver.resolve(FieldValidation.class))
                .additionalModels(typeResolver.resolve(ProblemWithField.class))
                .apiInfo(apiInfo())
                .tags(new Tag("City", "City of state"))
                .tags(new Tag("Demand", "Demand of Restaurant"));
    }

    private List<Response> globalPatchResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .description("Recurso não encontrado ou não existente.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .description("Envio de formulario com erro, não teve os preenchimentos necessario ou algo do tipo.")
                        .build()
        );
    }

    private List<Response> globalPostResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .description("Recurso não encontrado ou não existente.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .description("Envio de formulario com erro, não teve os preenchimentos necessario ou algo do tipo.")
                        .build()
        );
    }

    private Consumer<RepresentationBuilder> getProblemWithFieldModelReference() {
        return r -> r.model(m -> m.name("ProblemWithField")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("ProblemWithField").namespace("com.messimari.restaurantml.api.handler")))));
    }

    private Consumer<RepresentationBuilder> getProblemModelReference() {
        return r -> r.model(m -> m.name("Problem")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problem").namespace("com.messimari.restaurantml.api.handler")))));
    }

    private List<Response> globalPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .description("Recurso não encontrado ou não existente.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .description("Envio de formulario com erro, não teve os preenchimentos necessario ou algo do tipo.")
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.CONFLICT.value()))
                        .description("Recurso não pode ser excluido por ja esta sendo usado")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .description("Recurso não encontrado ou não existente.")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .description("Envio de formulario com erro, não teve os preenchimentos necessario ou algo do tipo.")
                        .build()
        );
    }
    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso não encontrado ou não existente.")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemWithFieldModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .description("Envio de formulario com erro, não teve os preenchimentos necessario ou algo do tipo.")
                        .build()
        );
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RestaurantML")
                .description("Api de restaurant")
                .version("1")
                .contact(new Contact("Messias", "https://www.linkedin.com/in/messias-dev-silva/", "messias.silva_dev@hotmail.com"))
                .build();
    }
}
