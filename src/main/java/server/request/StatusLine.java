package main.java.server.request;

public class StatusLine {
    private String method;
    private String path;
    private String version;

    public StatusLine(String method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getVersion() {
        return version;
    }
}
