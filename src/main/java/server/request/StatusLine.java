package main.java.server.request;

import main.java.server.HTTPMessageComponents.HTTPMethods;

public class StatusLine {
    private HTTPMethods method;
    private String path;
    private String version;

    public StatusLine(HTTPMethods method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public HTTPMethods getMethod() {
        return method;
    }

    public String getVersion() {
        return version;
    }
}
