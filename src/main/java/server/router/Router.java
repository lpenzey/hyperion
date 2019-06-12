package main.java.server.router;

import main.java.server.Response;
import main.java.server.request.Request;

import java.io.IOException;

public interface Router {
    Response route(Request request) throws IOException;
}