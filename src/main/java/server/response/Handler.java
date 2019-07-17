package main.java.server.response;

import main.java.server.request.Request;

@FunctionalInterface
public interface Handler {
    Response generateResponse(Request request);
}