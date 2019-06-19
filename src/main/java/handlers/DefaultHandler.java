package main.java.handlers;

import main.java.server.response.Response;
import main.java.server.request.Request;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.NOT_FOUND;

public class DefaultHandler implements Handler {
    @Override
    public Response generateResponseForRequest(Request request) {
        return new Response(VERSION + SP + NOT_FOUND + CRLF);
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String[] allowedMethods() {
        return null;
    }
}
