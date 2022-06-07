package com.devcris.ofertas.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Value("${empleosApp.rout.img}")
    private String rutaImg;

    @Value("${empleosApp.rout.cv}")
    private String rutaDocs;

    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
       
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImg); // Windows
        registry.addResourceHandler("/docs/**").addResourceLocations("file:"+rutaDocs);
        }
        
}
