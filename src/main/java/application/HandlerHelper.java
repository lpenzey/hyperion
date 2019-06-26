package main.java.application;

import main.java.server.request.Request;
import main.java.server.response.Handler;
import main.java.server.response.Response;
import main.java.server.response.ResponseBuilder;

import java.util.HashMap;

class HandlerHelper {

    private static ResponseBuilder builder = new ResponseBuilder();

    static Response assembleResponse(Request request, String body) {
        HashMap<String, Handler> pathMethods = Routes.ROUTES.get(request.getRequestPath());

        return builder.build(request, body, pathMethods);
    }

    static Response redirect(Request request, String redirectLocation) {
        return builder.redirect(request, redirectLocation);
    }
}
