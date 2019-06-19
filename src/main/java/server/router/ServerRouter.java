package main.java.server.router;

import main.java.handlers.DefaultHandler;
import main.java.handlers.Handler;
import main.java.handlers.HandlerCollector;
import main.java.server.response.Response;
import main.java.server.request.Request;

public class ServerRouter implements Router {

    @Override
    public Response route(Request request) {
        Route route = new Route(request.getRequestMethod(), request.getRequestPath());
        var allHandlers = HandlerCollector.ALL_HANDLERS;

        for (Handler handler : allHandlers) {
            if (route.getPath().equals(handler.getPath())) {
                return handler.generateResponseForRequest(request);
            }

        }
        return new DefaultHandler().generateResponseForRequest(request);
    }
}
