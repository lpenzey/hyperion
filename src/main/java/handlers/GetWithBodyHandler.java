package main.java.handlers;

import main.java.server.HTTPMessageComponents.HTTPMethods;
import main.java.server.request.Request;
import main.java.server.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.NOT_ALLOWED;
import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class GetWithBodyHandler implements Handler {

    @Override
    public Response generateResponseForRequest(Request request) {
        if (!methodIsAllowed(request.getRequestMethod())) {
            return new Response(VERSION + SP + NOT_ALLOWED + CRLF, buildHeaders());
        }
        return new Response(VERSION + SP + OK + CRLF);
    }

    @Override
    public String getPath() {
        return "/get_with_body";
    }

    @Override
    public String[] allowedMethods() {
        return new String[]{HTTPMethods.HEAD,
                            HTTPMethods.OPTIONS};
    }

    private HashMap<String, String> buildHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        String allowedMethods = String.join(",", allowedMethods());
        headers.put("Allow", allowedMethods);
        return headers;
    }

    private boolean methodIsAllowed(String method) {
        List<String> headersList = Arrays.asList(allowedMethods());
        return headersList.contains(method);
    }
}
