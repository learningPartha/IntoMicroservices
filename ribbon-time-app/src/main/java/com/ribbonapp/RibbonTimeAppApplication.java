package com.ribbonapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.ribbonconfig.RibbonTimeConfig;

//@EnableDiscoveryClient//to be used when using service discovery to communicate and load balance
@SpringBootApplication
@RibbonClient(name="time-service", configuration=RibbonTimeConfig.class)//when using ribbon client to communicate with other service
public class RibbonTimeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeAppApplication.class, args);
	}

}
