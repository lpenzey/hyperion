package main.java.server.response;

import main.java.application.Handler;
import main.java.server.request.Request;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.*;

public class ResponseBuilder {

    public Response build(Request request, String body, TreeMap<String, HashMap<String, Handler>> routes) {
        return new Response(VERSION + SP + OK + CRLF, addAllowHeaders(routes.get(request.getRequestPath())), body);
    }


    Response notFound(Request request, TreeMap<String, HashMap<String, Handler>> routes) {
        return new Response(VERSION + SP + NOT_FOUND + CRLF);
    }

    private HashMap<String, String> addAllowHeaders(HashMap<String, Handler> route) {
        HashMap<String, String> headers = new HashMap<>();
        Set<String> methodSet = route.keySet();
        String allowedMethods = String.join(",", methodSet);
        headers.put("Allow", allowedMethods);
        return headers;
    }

    Response notAllowed(Request request, TreeMap<String, HashMap<String, Handler>> routes) {
        return new Response(VERSION + SP + NOT_ALLOWED + CRLF, addAllowHeaders(routes.get(request.getRequestPath())));

    }
}
