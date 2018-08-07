package com.preguntation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PreguntationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(PreguntationApplication.class, args);

        System.out.println("This is a test");
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PreguntationApplication.class);
    }
}
