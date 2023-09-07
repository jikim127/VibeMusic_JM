package com.example.vibemusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/assets/**").
                addResourceLocations("classpath:/static/assets/");
        registry.addResourceHandler("/.saas-cache/**")
                .addResourceLocations("classpath:/static/.saas-cache/");
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("classpath:/static/audio/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/scss/**")
                .addResourceLocations("classpath:static/scss/");

    }
}
