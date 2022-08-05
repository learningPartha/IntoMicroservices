package com.springcloud;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Hello implements Function<APIGatewayProxyRequestEvent
        , APIGatewayProxyResponseEvent> {


    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent inputRequest) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(200);
        responseEvent.setBody("Hello reached Spring cloud function with message : "+inputRequest.getBody());
        return responseEvent;
    }
}
