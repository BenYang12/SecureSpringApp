package com.benyang12.securespringapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //Tells spring boot that this class contains configuration for the web layer
public class MvcConfig implements WebMvcConfigurer { //Override some of SpringMVC's methods
    //controller is essentially middleman
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");//if someone visits http://localhost:8080/home -> Spring looks for a Thymeleaf template named home.html
        registry.addViewController("/").setViewName("home"); //root
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hello").setViewName("hello");

    }
}