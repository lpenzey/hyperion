package main.java.server.request;

public class StatusLine {
    private String method;
    private String path;
    private String version;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setVersion(String version) {
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
