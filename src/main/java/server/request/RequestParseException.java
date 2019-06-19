package main.java.server.request;

public class RequestParseException extends Exception {
    public RequestParseException (Exception ex) {
        super(ex);
    }
}
