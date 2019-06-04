package main.java.server.request;

import java.io.IOException;
import java.util.HashMap;

public class RequestParser {

    private String[] segmentedRequest;
    private final HashMap<String, String> headers = new HashMap<>();

    public Request create(String incomingRequest) {
        this.segmentedRequest  = incomingRequest.split("\r\n")[0].split(" ");
        Request request = new Request();
        HashMap<String, String> headers = buildHeaders();
        request.setHeaders(headers);
        return request;
    }

    private HashMap<String, String> buildHeaders() {
        String method = getMethod();
        String path = getPath();
        String version = getVersion();
        headers.put("method", method);
        headers.put("path", path);
        headers.put("version", version);
        return headers;
    }

    private String getMethod() { return segmentedRequest[0]; }

    private String getPath() {
        return segmentedRequest[1];
    }

    private String getVersion() {
        return segmentedRequest[2];
    }
}
