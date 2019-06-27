package main.java.server.request;

import java.util.HashMap;

public class Headers {

    private HashMap<String, String> requestHeaders;

    public Headers() {
        this.requestHeaders = new HashMap<>();
    }

    public void addHeader(String key, String value) {
        this.requestHeaders.put(key, value);
    }

    public HashMap<String, String> getHeaders() {
        return requestHeaders;
    }
}
