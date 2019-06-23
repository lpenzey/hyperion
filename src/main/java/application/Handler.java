package main.java.application;

import main.java.server.response.Response;
import main.java.server.request.Request;

@FunctionalInterface
public interface Handler {
    Response generateResponse(Request request);
}