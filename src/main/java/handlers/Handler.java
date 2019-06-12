package main.java.handlers;

import main.java.server.Response;
import main.java.server.request.Request;

public interface Handler {

    Response generateResponseForRequest(Request request);

    String getPath();

}