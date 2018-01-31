package org.cool.zoo.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Dang Dim
 * Date     : 20-Jan-18, 2:13 PM
 * Email    : d.dim@gl-f.com
 */

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/swagger").setViewName("swagger");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "OPTIONS", "DELETE")
                .allowedHeaders("X-Auth-Token", "Authorization", "Content-Type")
                //.exposedHeaders("custom-header1", "custom-header2")
                .allowCredentials(false)
                .maxAge(4800);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
    }


}

