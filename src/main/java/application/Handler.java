package main.java.handlers;

import main.java.server.response.Response;
import main.java.server.request.Request;

public interface Handler {

    Response generateResponseForRequest(Request request);

    String getPath();

    String[] allowedMethods();

}