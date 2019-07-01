package test.java.server.response;

import main.java.server.response.Response;
import org.junit.Test;


import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static org.junit.Assert.assertEquals;

import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class ResponseTest {

    @Test
    public void statusLineIsCreatedCorrectly() {
        Response response = new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .build();

        assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
    }

    @Test
    public void responseCreatedWithStatusLineAndHeaders() {
        Response response = new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .withHeader("Allow:", "GET, HEAD, OPTIONS")
                .build();

        assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
        assertEquals("{Allow:=GET, HEAD, OPTIONS}", response.getHeaders().toString());
    }
}
