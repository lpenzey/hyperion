package main.java.server.request;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.CRLF;

public class RequestParser {
    private static final int REQUEST_METHOD_INDEX = 0;
    private static final int REQUEST_PATH_INDEX = 1;
    private static final int REQUEST_VERSION_INDEX = 2;

    public Request create(String incomingRequest) throws RequestParseException {
        try {
            StatusLine requestStatusLine = buildStatusLine(incomingRequest);
            Headers requestHeaders = buildHeaders(incomingRequest);
            String requestBody = buildBody(incomingRequest);
            return new Request(requestStatusLine, requestHeaders, requestBody);
        } catch (Exception e) {
            throw new RequestParseException(e);
        }
    }

    private StatusLine buildStatusLine(String incomingRequest) {
        String[] segmentedStatusLine = incomingRequest.split(CRLF)[0].split(" ");

        return new StatusLine(
                getMethod(segmentedStatusLine),
                getPath(segmentedStatusLine),
                getVersion(segmentedStatusLine));
    }

    private Headers buildHeaders(String incomingRequest) {
        String[] segmentedHeaders = incomingRequest.split(CRLF + CRLF)[0].split("\n");
        Headers requestHeaders = new Headers();

        for (int i = 1; i < segmentedHeaders.length; i++) {
            String[] header = segmentedHeaders[i].split(":");
            requestHeaders.addHeader(header[0].trim(), header[1].trim());
        }

        return requestHeaders;
    }

    private String buildBody(String incomingRequest) {
        String[] segmentedRequest = incomingRequest.split(CRLF + CRLF);
        if (segmentedRequest.length > 1) {
            return segmentedRequest[1];
        }
        return "";
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
