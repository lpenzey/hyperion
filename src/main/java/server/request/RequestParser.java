package main.java.server.request;

public class RequestParser {
    private static final int REQUEST_METHOD_INDEX = 0;
    private static final int REQUEST_PATH_INDEX = 1;
    private static final int REQUEST_VERSION_INDEX = 2;

    public Request create(String incomingRequest) throws RequestParseException {
        try {
            String[] segmentedStatusLine = incomingRequest.split("\r\n")[0].split(" ");
            StatusLine requestStatusLine = buildStatusLine(segmentedStatusLine);
            return new Request(requestStatusLine);
        } catch (Exception e) {
            throw new RequestParseException(e);
        }
    }

    private StatusLine buildStatusLine(String[] segmentedStatusLine) {
        StatusLine requestStatusLine = new StatusLine();
        requestStatusLine.setMethod(getMethod(segmentedStatusLine));
        requestStatusLine.setPath(getPath(segmentedStatusLine));
        requestStatusLine.setVersion(getVersion(segmentedStatusLine));

        return requestStatusLine;
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
