package test.java.server.response;

import main.java.application.Routes;
import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import main.java.server.response.Handler;
import main.java.server.response.Response;
import main.java.server.response.ResponseBuilder;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void createsResponseWithAllowedMethodsHeaders() {
        Request request = new Request(new StatusLine("OPTIONS", "/method_options", "HTTP/1.1"));
        ResponseBuilder builder = new ResponseBuilder();
        HashMap<String, Handler> pathMethods = Routes.ROUTES.get(request.getRequestPath());

        Response response = builder.build(request, "", pathMethods);

        assertEquals("{Allow=HEAD,GET,OPTIONS}", response.getHeaders().toString());
    }
}
