package com.appclient2.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppClientController {

    @Autowired//need eureka client to find service
    private EurekaClient eurekaClient;

    @Autowired//need rest template to call other service
    private RestTemplateBuilder restTemplateBuilder;

    @RequestMapping("/")
    public String callService(){
        //create new rest template
        RestTemplate restTemplate = restTemplateBuilder.build();
        //get service server detail , since non secure ,hence false
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("app-service2",false);
        String baseUrl = instanceInfo.getHomePageUrl();

        //generate response// calling get method //no specific request body hence null // response is string
        ResponseEntity<String> response =
                restTemplate.exchange(baseUrl, HttpMethod.GET,null, String.class);
        return response.getBody();
    }

}
