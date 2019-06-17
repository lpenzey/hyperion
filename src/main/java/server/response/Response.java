package main.java.server.response;

import java.util.HashMap;

public class Response {
    private String body;
    private String statusLine;
    private HashMap<String, String> headers;

    public Response(String statusLine) {
        this.statusLine = statusLine;
    }

    public Response(String statusLine, HashMap<String, String> headers) {
        this.statusLine = statusLine;
        this.headers = headers;
    }

    public Response(String statusLine, HashMap<String, String> headers, String body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}