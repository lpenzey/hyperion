package main.java.server.request;

import java.util.HashMap;

public class RequestParser {
    private static final int REQUEST_METHOD_INDEX = 0;
    private static final int REQUEST_PATH_INDEX = 1;
    private static final int REQUEST_VERSION_INDEX = 2;

    public Request create(String incomingRequest) {
        String[] segmentedRequest = incomingRequest.split("\r\n")[0].split(" ");
        Request request = new Request();
        HashMap<String, String> headers = new HashMap<>();
        request.setHeaders(buildHeaders(segmentedRequest, headers));
        return request;
    }

    private HashMap<String, String> buildHeaders(String[] segmentedRequest, HashMap<String, String> headers) {
        headers.put("method", getMethod(segmentedRequest));
        headers.put("path", getPath(segmentedRequest));
        headers.put("version", getVersion(segmentedRequest));
        return headers;
    }

    private String getMethod(String[] segmentedRequest) {
        return segmentedRequest[REQUEST_METHOD_INDEX];
    }

    private String getPath(String[] segmentedRequest) {
        return segmentedRequest[REQUEST_PATH_INDEX];
    }

    private String getVersion(String[] segmentedRequest) {
        return segmentedRequest[REQUEST_VERSION_INDEX];
    }
}
