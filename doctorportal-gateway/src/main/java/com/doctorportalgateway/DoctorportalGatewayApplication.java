package com.doctorportalgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class DoctorportalGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorportalGatewayApplication.class, args);
	}

}