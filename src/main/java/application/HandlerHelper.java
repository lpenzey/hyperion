package main.java.application;

import main.java.server.request.Request;
import main.java.server.response.Response;
import main.java.server.response.ResponseBuilder;

class HandlerHelper {

    static Response assembleResponse(Request request, String body) {
        ResponseBuilder builder = new ResponseBuilder();
        return builder.build(request, body, Routes.getROUTES());
    }

}
