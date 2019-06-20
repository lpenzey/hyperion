package main.java.server.response;

import main.java.application.App;
import main.java.server.request.Request;

public class PathFinder {
    private final App app;
    private Request request;

    public PathFinder(Request request, App app) {
        this.request = request;
        this.app = app;
    }

    public Response selectHandler() {

         if (!pathExists()) {
             ResponseBuilder builder = new ResponseBuilder();
             return builder.notFound(request, app.routes());
         }

        if (!methodAllowed()) {
            ResponseBuilder builder = new ResponseBuilder();
            return builder.notAllowed(request, app.routes());
        }

        return app.routes().get(request.getRequestPath()).get(request.getRequestMethod()).generateResponse(request);
    }

    private boolean pathExists() {
        return app.routes()
                    .keySet()
                    .contains(request.getRequestPath());
    }

    private boolean methodAllowed() {
        return app.routes()
                    .get(request.getRequestPath())
                    .containsKey(request.getRequestMethod());
    }
}
