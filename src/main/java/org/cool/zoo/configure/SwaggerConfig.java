package org.cool.zoo.configure;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.lang.model.type.WildcardType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    private static final Contact MY_CONTACT = new Contact("bucky", "www.google.com", "buckymini@gmail.com");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.cool.zoo.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .pathMapping("/")
                .produces(produces())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.
                        resolve(ResponseEntity.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder()
                        .code(500).message("500 Forbidden")
                        .responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder()
                        .code(404).message("404 Not found")
                        .responseModel(new ModelRef("Error")).build()))
                .globalResponseMessage(RequestMethod.PATCH, newArrayList(new ResponseMessageBuilder()
                        .code(500).message("500 Forbidden")
                        .responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder()
                        .code(404).message("404 Not found")
                        .responseModel(new ModelRef("Error")).build()))
                .globalResponseMessage(RequestMethod.POST, newArrayList(new ResponseMessageBuilder()
                        .code(500).message("500 Forbidden")
                        .responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder()
                        .code(404).message("404 Not found")
                        .responseModel(new ModelRef("Error")).build()))
                .securitySchemes(apiKeys())
                .securityContexts(newArrayList(securityContext()));
    }

    private Set<String> produces() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");

        return produces;
    }

    ApiInfo apiInfo() {
        return new ApiInfo(
                "Web Echo ZOO",
                "Some custom description of API",
                "V.1.0",
                "Terms of service echo",
                MY_CONTACT,
                "Zoo APP",
                "buckymini@gmail.com",
                new ArrayList<>()
        );
    }

    public SecurityScheme security() {
        return new OAuthBuilder()
                .name("oauth2")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    SwaggerResource swaggerResource() {
        return new SwaggerResource();
    }

    /**
     * swagger security oauth2 configuration with header
     * @return securityConfiguration
     */

    @Bean
    SecurityConfiguration securityConfiguration() {
        return new SecurityConfiguration(//<19>
                "developer",
                "secret",
                null,
                null,
                null,
                ApiKeyVehicle.HEADER, //<23>
                "Authorization", //<24>
                ","); //scope separator);
    }


    private List<AuthorizationScope> scopes() {
        return newArrayList(
                new AuthorizationScope("read", "modify read pets in your account"),
                new AuthorizationScope("write", "Modify write pets in your account"));
    }

    private List<GrantType> grantTypes() {

        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:9191/oauth/token");

        return newArrayList(grantType);
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                null,// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }


    /**
     * A class to represent a default set of authorizations
     * to apply to each api operation To customize which request
     * mappings the list of authorizations are applied to Specify t
     * he custom includePatterns or requestMethods
     * @return
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder().
                securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read only");
        authorizationScopes[1] = new AuthorizationScope("write", "read and write");
        authorizationScopes[2] = new AuthorizationScope("trust", "trust client");
        List<SecurityReference> list = new ArrayList<>();
        SecurityReference s = new SecurityReference("Authorization", authorizationScopes);
        list.add(s);
        return list;
    }

    private List<SecurityScheme> apiKeys() {
        ArrayList<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(security());
        securitySchemes.add(new ApiKey("Authorization", "Authorization", "header"));
        return securitySchemes;
    }

}
