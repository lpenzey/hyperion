package main.java.server;

public class Response {
    private String statusLine;

    public Response(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getStatusLine() {
        return statusLine;
    }

}