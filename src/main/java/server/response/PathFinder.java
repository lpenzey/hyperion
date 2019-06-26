package main.java.server.response;

import main.java.application.App;
import main.java.server.request.Request;

import java.util.HashMap;
import java.util.TreeMap;

public class PathFinder {
    private final App app;
    private static ResponseBuilder builder = new ResponseBuilder();

    public PathFinder(App app) {
        this.app = app;
    }

    public Response getResponse(Request request) {

         if (!pathExists(request)) {

             return builder.notFound(request);
         }

        if (!methodAllowed(request)) {
            HashMap<String, Handler> pathMethods = app.routes().get(request.getRequestPath());

            return builder.notAllowed(request, pathMethods);
        }

        TreeMap<String, HashMap<String, Handler>> routes = app.routes();
        HashMap<String, Handler> handlerCollection = routes.get(request.getRequestPath());
        Handler routeHandler = handlerCollection.get(request.getRequestMethod());

        return routeHandler.generateResponse(request);
    }

    private boolean pathExists(Request request) {
        return app.routes()
                    .keySet()
                    .contains(request.getRequestPath());
    }

    private boolean methodAllowed(Request request) {
        return app.routes()
                    .get(request.getRequestPath())
                    .containsKey(request.getRequestMethod());
    }
}
