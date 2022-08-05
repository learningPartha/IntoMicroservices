package org.lambda.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Collections;

public class LambdaJavaApi implements RequestHandler<Object,GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(Object object, Context context) {
        String message = "Hello World!";
        System.out.println(message);
        GatewayResponse gatewayResponse= new GatewayResponse(message,200,
                Collections.singletonMap("X-Powered-By","Testing"),
                false);
        return gatewayResponse;
    }
}
