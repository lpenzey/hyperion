package main.java.application;

import main.java.server.request.Request;
import main.java.server.response.Handler;

class Handlers {
    static final Handler SimpleGet = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler GetWithBody = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler SimpleOptions = (Request request) -> HandlerHelper.assembleResponse(request, "");
    static final Handler EchoBody = (Request request) -> HandlerHelper.assembleResponse(request, request.getBody());
    static final Handler Redirect = (Request request) -> HandlerHelper.redirect(request, "http://127.0.0.1:5000/simple_get");
}