package com.job4j.cars.utils;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableConfigurationProperties
@EnableWebMvc
public class UploadFileProperties implements WebMvcConfigurer {

    //@Value("${upload.path}")
    private String uploadPath = "D:/Java_projects/images/cars/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("img/**")
                .addResourceLocations("file:/D:/Java_projects/images/cars/");
    }


    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

}
