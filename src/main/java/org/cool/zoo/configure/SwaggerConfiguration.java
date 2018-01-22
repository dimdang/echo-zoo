package org.cool.zoo.configure;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dang Dim
 * Date     : 20-Jan-18, 2:09 PM
 * Email    : d.dim@gl-f.com
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.cool.zoo.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Collections.singletonList(securitySchema()));
    }

    /*@Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                null,
                "none",
                "alpha",
                "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,
                false,
                60000L);
    }*/

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = Arrays.asList(
                new AuthorizationScope(Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_READ, Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_READ_DESC),
                new AuthorizationScope(Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_WRITE, Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_WRITE_DESC)
        );

        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:9191/oauth/token");
        List<GrantType> grantTypes = Collections.singletonList(resourceOwnerPasswordCredentialsGrant);

        return new OAuth("AUTHORIZATION", authorizationScopeList, grantTypes);
    }

    /*private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "cool-zoo-api",
                "Some custom description of API.",
                "API Z1",
                "Terms of service",
                new Contact("Bucky Lazy", "www.google.com", "dangdim02@gmail.com"),
                "License of API",
                "API license URL");
        return apiInfo;
    }*/

    @Bean
    springfox.documentation.swagger.web.SecurityConfiguration security() {
        return new springfox.documentation.swagger.web.SecurityConfiguration(
                null,
                null,
                null,
                null,
                "Bearer access_token",
                ApiKeyVehicle.HEADER,
                "AUTHORIZATION",
                ","
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope(Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_READ, Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_READ_DESC),
                new AuthorizationScope(Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_WRITE, Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_WRITE_DESC)
        };
        return Lists.newArrayList(new SecurityReference(Oauth2Configuration.AuthorizationServerConfiguration.SCOPE_READ, authorizationScopes));
    }

}
