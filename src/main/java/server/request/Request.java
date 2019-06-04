package main.java.server.request;

import java.util.HashMap;

public class Request {

    private HashMap<String, String> headers;
    private String body;

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}