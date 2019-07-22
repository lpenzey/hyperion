package main.java.server.request;

import main.java.server.HTTPMessageComponents.HTTPMethods;

import java.util.HashMap;

public class Request {

    private String body;
    private StatusLine requestStatusLine;
    private Headers requestHeaders;

    public Request(StatusLine requestStatusLine) {
        this.requestStatusLine = requestStatusLine;
    }

    public Request(StatusLine requestStatusLine, Headers requestHeaders) {
        this.requestStatusLine = requestStatusLine;
        this.requestHeaders = requestHeaders;
    }

    public Request(StatusLine requestStatusLine, Headers requestHeaders, String body) {
        this.requestStatusLine = requestStatusLine;
        this.requestHeaders = requestHeaders;
        this.body = body;
    }

    public String getRequestPath() {
        return requestStatusLine.getPath();
    }

    public HTTPMethods getRequestMethod() {
        return requestStatusLine.getMethod();
    }

    public String getRequestVersion() {
        return requestStatusLine.getVersion();
    }

    public HashMap<String, String> getHeaders() {
        return requestHeaders.getHeaders();
    }

    public String getBody() {
        return body;
    }
}