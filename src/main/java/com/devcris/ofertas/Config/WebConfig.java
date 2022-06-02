package com.devcris.ofertas.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Value("${empleosApp.rout.img}")
    private String rutaImg;

    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
       
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImg); // Windows
        }
        
}
