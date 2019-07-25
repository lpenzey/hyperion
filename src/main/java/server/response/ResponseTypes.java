package main.java.server.response;

import main.java.application.Routes;
import main.java.server.ProxyHandler;
import main.java.server.request.Request;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class ResponseTypes {

    public static Response assembleResponse(Request request, String body) {
        HashMap<String, Handler> pathMethods = Routes.ROUTES.get(request.getRequestPath());
        Set<String> methodSet = pathMethods.keySet();
        String allowedMethods = String.join(",", methodSet);
        return new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .withHeader(ALLOW, allowedMethods)
                .withBody(body)
                .build();
    }

    public static Response redirect(Request request, String redirectLocation) {
        String hostName = request.getHeaders().get(HOST);

        return new Response.Builder()
                .withStatus(VERSION + SP + REDIRECT + CRLF)
                .withHeader(LOCATION, URL_PREFIX + hostName + redirectLocation)
                .build();
    }

    public static Response notFound(Request request) {
        return new Response.Builder()
                .withStatus(VERSION + SP + NOT_FOUND + CRLF)
                .build();
    }

    public static Response notAllowed(Request request, HashMap<String, Handler> pathMethods) {
        Set<String> methodSet = pathMethods.keySet();
        String allowedMethods = String.join(",", methodSet);
        return new Response.Builder()
                .withStatus(VERSION + SP + NOT_ALLOWED + CRLF)
                .withHeader(ALLOW, allowedMethods)
                .build();
    }

    public static Response internalError() {
        return new Response.Builder()
                .withStatus(VERSION + SP + INTERNAL_ERROR + CRLF)
                .build();
    }

    public static Response proxy(Request request, String s) {
        String proxyPayload;
        try {
            proxyPayload = ProxyHandler.sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseTypes.internalError();
        }
        return new Response.Builder()
                .withStatus(VERSION + SP + OK+ CRLF)
                .withBody(proxyPayload)
                .build();
    }
}
