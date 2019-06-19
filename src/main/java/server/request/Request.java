package main.java.server.request;

import java.util.HashMap;

public class Request {

    private StatusLine requestStatusLine;
    private Headers requestHeaders;

    public Request(StatusLine requestStatusLine) {
        this.requestStatusLine = requestStatusLine;
    }

    public Request(StatusLine requestStatusLine, Headers requestHeaders) {
        this.requestStatusLine = requestStatusLine;
        this.requestHeaders = requestHeaders;
    }

    public String getRequestPath() {
        return requestStatusLine.getPath();
    }

    public String getRequestMethod() {
        return requestStatusLine.getMethod();
    }

    public String getRequestVersion() {
        return requestStatusLine.getVersion();
    }

    public HashMap<String, String> getHeaders() {
        return requestHeaders.getHeaders();
    }
}