package test.java.server;

import main.java.server.response.Response;
import org.junit.Test;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static org.junit.Assert.assertEquals;

import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class ResponseTest {

    @Test
    public void statusLineIsCreatedCorrectly() {
        Response response = new Response(VERSION + SP + OK + CRLF);

        assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
    }
}
