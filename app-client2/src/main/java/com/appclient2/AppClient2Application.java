package com.appclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AppClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(AppClient2Application.class, args);
    }

}
