package main.java.server.request;

public class Request {

    private StatusLine requestStatusLine;

    public Request(StatusLine requestStatusLine) {
        this.requestStatusLine = requestStatusLine;
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
}