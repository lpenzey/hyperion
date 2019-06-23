package main.java.application;

import main.java.server.request.Request;

class Handlers {
    static final Handler SimpleGet = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler GetWithBody = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler SimpleOptions = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler EchoBody = (Request request) -> HandlerHelper.assembleResponse(request, "Request body");

}