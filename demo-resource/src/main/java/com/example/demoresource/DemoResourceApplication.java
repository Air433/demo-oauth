package com.example.demoresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoResourceApplication.class, args);
    }
}
