package com.david;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;

@SpringBootApplication
public class RabbitDemoApp extends SpringBootServletInitializer {

    @Autowired
    RabbitSetup rabbitSetup;

    @Autowired
    Subscriber subscriber;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RabbitDemoApp.class, args);

    }
}
