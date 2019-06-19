package main.java.server.request;

import java.util.HashMap;

public class Headers {

    private HashMap<String, String> requestHeaders;

    public void setHeaders(HashMap<String, String> headers) {
        this.requestHeaders = headers;
    }

    public HashMap<String, String> getHeaders() {
        return requestHeaders;
    }
}
