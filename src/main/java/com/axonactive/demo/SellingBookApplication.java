package com.axonactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SellingBookApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app.sources(SellingBookApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SellingBookApplication.class, args);
    }

}
