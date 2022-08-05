package org.lambda.aws;

import java.util.Map;

public class GatewayResponse {
    private String body;
    private Integer statusCode;
    private Map<String,String> headers;
    private boolean isBase64Encoder;

    public GatewayResponse(String body, Integer statusCode, Map<String, String> headers, boolean isBase64Encoder) {
        this.body = body;
        this.statusCode = statusCode;
        this.headers = headers;
        this.isBase64Encoder = isBase64Encoder;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isBase64Encoder() {
        return isBase64Encoder;
    }

    public void setBase64Encoder(boolean base64Encoder) {
        isBase64Encoder = base64Encoder;
    }
}
