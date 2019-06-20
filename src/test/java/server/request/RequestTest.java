package test.java.server.request;

import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import org.junit.Before;
import org.junit.Test;

import static main.java.server.HTTPMessageComponents.HTTPMethods.GET;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.VERSION;
import static org.junit.Assert.assertEquals;

public class RequestTest {
    private StatusLine requestStatusLine;
    private Request request;


    @Test
    public void statusLineIsCreatedCorrectly() {
        String SIMPLE_GET_PATH = "/simple_get";
        requestStatusLine = new StatusLine(GET, SIMPLE_GET_PATH, VERSION);


        assertEquals(GET, requestStatusLine.getMethod());
        assertEquals(SIMPLE_GET_PATH, requestStatusLine.getPath());
        assertEquals(VERSION, requestStatusLine.getVersion());
    }
}

