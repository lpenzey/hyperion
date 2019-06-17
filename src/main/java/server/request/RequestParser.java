package main.java.server.request;

import java.util.HashMap;

public class RequestParser {
    private static final int REQUEST_METHOD_INDEX = 0;
    private static final int REQUEST_PATH_INDEX = 1;
    private static final int REQUEST_VERSION_INDEX = 2;

    public Request create(String incomingRequest) throws RequestParseException {
        try {
            StatusLine requestStatusLine = buildStatusLine(incomingRequest);
            Headers requestHeaders = buildHeaders(incomingRequest);
            return new Request(requestStatusLine, requestHeaders);
        } catch (Exception e) {
            throw new RequestParseException(e);
        }
    }

    private StatusLine buildStatusLine(String incomingRequest) {
        String[] segmentedStatusLine = incomingRequest.split("\r\n")[0].split(" ");

        StatusLine requestStatusLine = new StatusLine();

        requestStatusLine.setMethod(getMethod(segmentedStatusLine));
        requestStatusLine.setPath(getPath(segmentedStatusLine));
        requestStatusLine.setVersion(getVersion(segmentedStatusLine));

        return requestStatusLine;
    }

    private Headers buildHeaders(String incomingRequest) {
        String[] segmentedHeaders = incomingRequest.split("\r\n\r\n")[0].split("\n");
        Headers requestHeaders = new Headers();

        HashMap<String, String> headersMap = new HashMap<String, String>();
        for (int i = 1; i < segmentedHeaders.length; i++) {
            String[] header = segmentedHeaders[i].split(":");
            headersMap.put(header[0].trim(), header[1].trim());
        }

        requestHeaders.setHeaders(headersMap);

        return requestHeaders;
    }

    private String getMethod(String[] segmentedStatusLine) {
        return segmentedStatusLine[REQUEST_METHOD_INDEX];
    }

    private String getPath(String[] segmentedStatusLine) {
        return segmentedStatusLine[REQUEST_PATH_INDEX];
    }

    private String getVersion(String[] segmentedStatusLine) {
        return segmentedStatusLine[REQUEST_VERSION_INDEX];
    }
}
