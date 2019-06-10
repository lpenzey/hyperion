package main.java.server;

class StatusCodes {
    private StatusCodes() {}

    public static final String VERSION = "HTTP/1.1";
    public static final String SP = " ";
    public static final String CRLF = "\r\n";
    public static final String NOT_FOUND = "HTTP/1.1 404 Not Found\r\n";
    public static final String OK = "HTTP/1.1 200 OK\r\n";
}
