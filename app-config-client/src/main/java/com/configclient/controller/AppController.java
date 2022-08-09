package com.configclient.controller;

import com.configclient.config.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AppController {
    @Autowired
    private AppConfiguration properties;

    @Value("${some.other.property}")
    private String someOtherProperty;

    @RequestMapping("/")
    public String printConfig(){
        StringBuilder sb = new StringBuilder();
        sb.append(properties.getProperty());
        sb.append(" || ");
        sb.append(someOtherProperty);
        return sb.toString();
    }

}
