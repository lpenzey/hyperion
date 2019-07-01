package test.java.server;

import main.java.server.request.Headers;
import main.java.server.response.Handler;
import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import main.java.server.response.Response;

import main.java.server.Router;
import main.java.server.response.ResponseTypes;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static main.java.server.HTTPMessageComponents.HTTPMethods.*;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;

public class RouterTest {
    private Router router;

    @Before
    public void setUp() {
        router = new Router();
        router.get("/simple_get", HandlersStub.SimpleGet);
        router.head("/simple_get", HandlersStub.SimpleGet);
        router.get("/redirect", HandlersStub.Redirect);
        router.head("/get_with_body", HandlersStub.GetWithBody);
        router.options("/get_with_body", HandlersStub.GetWithBody);
        router.get("/method_options", HandlersStub.SimpleOptions);
        router.head("/method_options", HandlersStub.SimpleOptions);
        router.options("/method_options", HandlersStub.SimpleOptions);
        router.put("/method_options_2", HandlersStub.EchoBody);
        router.post("/echo_body", HandlersStub.EchoBody);
    }

    @Test
    public void canProcessGETRequestIfRouteHasBeenAdded()  {
        Request simpleGetRequest = new Request(new StatusLine(GET, "/simple_get", VERSION));
        Response getResponse = router.generateResponse(simpleGetRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", getResponse.getStatusLine());
    }

    @Test
    public void canProcessHEADRequestIfRouteHasBeenAdded()  {
        Request headRequest = new Request(new StatusLine(HEAD, "/simple_get", VERSION));
        Response headResponse = router.generateResponse(headRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", headResponse.getStatusLine());
        assertTrue(headResponse.getBody().isEmpty());
    }

    @Test
    public void canProcessOPTIONSRequestIfRouteHasBeenAdded()  {
        Request optionsRequest = new Request(new StatusLine(OPTIONS, "/method_options", VERSION));
        Response optionsResponse = router.generateResponse(optionsRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", optionsResponse.getStatusLine());
        assertEquals("HEAD,GET,OPTIONS", optionsResponse.getHeaders().get("Allow"));
        assertTrue(optionsResponse.getBody().isEmpty());
    }

    @Test
    public void canProcessRedirectRequestIfRouteHasBeenAdded()  {
        Headers requestHeaders = new Headers();
        requestHeaders.addHeader("Host", "127.0.0.1:5000");
        Request redirectRequest = new Request(new StatusLine(GET, "/redirect", VERSION), requestHeaders, "");
        Response redirectResponse = router.generateResponse(redirectRequest);

        assertEquals("HTTP/1.1 301 Moved Permanently\r\n", redirectResponse.getStatusLine());
        assertEquals("http://127.0.0.1:5000/redirected_uri", redirectResponse.getHeaders().get("Location"));
        assertTrue(redirectResponse.getBody().isEmpty());
    }


    @Test
    public void canProcessPOSTRequestIfRouteHasBeenAdded()  {
        Headers requestHeaders = new Headers();
        requestHeaders.addHeader("Content-Length", "12");
        Request postRequest = new Request(new StatusLine(POST, "/echo_body", VERSION), requestHeaders, "Request body");

        Response postResponse = router.generateResponse(postRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", postResponse.getStatusLine());
        assertEquals("Request body", postResponse.getBody());
    }

    @Test
    public void doesNotAllowAccessToPathIfMethodNotAllowed()  {
        Request notAllowedRequest = new Request(new StatusLine(GET, "/get_with_body", VERSION));
        Response notAllowedResponse = router.generateResponse(notAllowedRequest);

        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", notAllowedResponse.getStatusLine());
        assertEquals("HEAD,OPTIONS", notAllowedResponse.getHeaders().get("Allow"));
        assertTrue(notAllowedResponse.getBody().isEmpty());
    }


    static class HandlersStub {
        static final Handler SimpleGet = (Request request) -> ResponseTypes.assembleResponse(request, "");
        static final Handler GetWithBody = (Request request) -> ResponseTypes.assembleResponse(request, "");
        static final Handler SimpleOptions = (Request request) -> ResponseTypes.assembleResponse(request, "");
        static final Handler EchoBody = (Request request) -> ResponseTypes.assembleResponse(request, request.getBody());
        static final Handler Redirect = (Request request) -> ResponseTypes.redirect(request, "/redirected_uri");
    }

}

