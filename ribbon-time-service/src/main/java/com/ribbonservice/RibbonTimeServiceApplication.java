package com.ribbonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient//to be used when using service discovery to communicate and load balance
@SpringBootApplication
public class RibbonTimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeServiceApplication.class, args);
	}

}
