package main.java.application;

import main.java.server.request.Request;
import main.java.server.response.Handler;
import main.java.server.response.ResponseTypes;

class Handlers {
    static final Handler SimpleGet = (Request request) -> ResponseTypes.assembleResponse(request, "");
    static final Handler GetWithBody = (Request request) -> ResponseTypes.assembleResponse(request, "");
    static final Handler SimpleOptions = (Request request) -> ResponseTypes.assembleResponse(request, "");
    static final Handler EchoBody = (Request request) -> ResponseTypes.assembleResponse(request, request.getBody());
    static final Handler Redirect = (Request request) -> ResponseTypes.redirect(request, "/simple_get");
    static final Handler Proxy = (Request request) -> ResponseTypes.proxy(request, request.getBody());
}