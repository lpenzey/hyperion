package test.java.server.request;

import main.java.server.request.Headers;
import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import org.junit.Test;

import static main.java.server.HTTPMessageComponents.HTTPMethods.GET;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.VERSION;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    @Test
    public void statusLineIsCreatedCorrectly() {
        String SIMPLE_GET_PATH = "/simple_get";
        StatusLine requestStatusLine = new StatusLine(GET, SIMPLE_GET_PATH, VERSION);


        assertEquals(GET, requestStatusLine.getMethod());
        assertEquals(SIMPLE_GET_PATH, requestStatusLine.getPath());
        assertEquals(VERSION, requestStatusLine.getVersion());
    }

    @Test
    public void requestIsCreatedWithStatusLineHeadersAndBody() {
        String SIMPLE_GET_PATH = "/simple_get";
        StatusLine requestStatusLine = new StatusLine(GET, SIMPLE_GET_PATH, VERSION);

        Headers headers = new Headers();
        headers.addHeader("Host", "localhost:5000");

        String body = "Request body";
        Request request = new Request(requestStatusLine, headers, body);


        assertEquals(GET, request.getRequestMethod());
        assertEquals(SIMPLE_GET_PATH, request.getRequestPath());
        assertEquals(VERSION, request.getRequestVersion());
        assertEquals("localhost:5000", request.getHeaders().get("Host"));
        assertEquals("Request body", request.getBody());
    }
}

