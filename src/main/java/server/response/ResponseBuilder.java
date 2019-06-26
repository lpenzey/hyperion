package main.java.server.response;

import main.java.server.request.Request;

import java.util.HashMap;
import java.util.Set;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.*;

public class ResponseBuilder {

    public Response build(Request request, String body, HashMap<String, Handler> pathMethods) {

        return new Response(VERSION + SP + OK + CRLF, addAllowHeaders(pathMethods), body);
    }

    public Response redirect(Request request, String redirectLocation) {
        HashMap<String, String> headers = addLocationHeader(redirectLocation);

        return new Response(VERSION + SP + REDIRECT + CRLF, headers, "");
    }

    public Response notFound(Request request) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("ALLOW", "GET,HEAD");

        return new Response(VERSION + SP + NOT_FOUND + CRLF, headers, NOT_FOUND);
    }

    public Response notAllowed(Request request, HashMap<String, Handler> pathMethods) {
        HashMap<String, String> headers = addAllowHeaders(pathMethods);

        return new Response(VERSION + SP + NOT_ALLOWED + CRLF, headers, "");
    }

    private HashMap<String, String> addLocationHeader(String redirectLocation) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Location", redirectLocation);

        return headers;
    }

    private HashMap<String, String> addAllowHeaders(HashMap<String, Handler> route) {
        HashMap<String, String> headers = new HashMap<>();
        Set<String> methodSet = route.keySet();
        String allowedMethods = String.join(",", methodSet);
        headers.put("Allow", allowedMethods);

        return headers;
    }
}
