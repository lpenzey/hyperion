package main.java.handlers;

import main.java.server.Response;
import main.java.server.request.Request;


import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class SimpleGetHandler implements Handler {

    @Override
    public Response generateResponseForRequest(Request request) {
        return new Response(VERSION + SP + OK + CRLF);
    }

    @Override
    public String getPath() {
        return "/simple_get";
    }
}
