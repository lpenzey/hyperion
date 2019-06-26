package test.java.server.response;

import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import main.java.server.response.PathFinder;
import main.java.server.response.Response;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PathFinderTest {
    private PathFinder pathFinder;
    private AppStub app;

    @Before public void setUp() {
        new RoutesStub();
        app = new AppStub(RoutesStub.ROUTES);
    }

    @Test
    public void findsMatchingRouteForSimpleGetRequestAndGeneratesResponse() {
        Request simpleGetRequest = new Request(new StatusLine("GET", "/simple_get", "HTTP1.1"));
        pathFinder = new PathFinder(app);
        Response simpleGetResponse = pathFinder.getResponse(simpleGetRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", simpleGetResponse.getStatusLine());
        }

    @Test
    public void returnsNotFoundResponseForUnknownRoute() {
        Request unknownRequest = new Request(new StatusLine("GET", "/WHO_GOES_THERE", "HTTP1.1"));
        pathFinder = new PathFinder(app);
        Response unknownResponse = pathFinder.getResponse(unknownRequest);

        assertEquals("HTTP/1.1 404 Not Found\r\n", unknownResponse.getStatusLine());
    }

    @Test
    public void returnsMethodNotAllowedForRequestWithForbiddenMethod() {
        Request wrongMethodRequest = new Request(new StatusLine("PUT", "/simple_get", "HTTP1.1"));
        pathFinder = new PathFinder(app);
        Response wrongMethodResponse = pathFinder.getResponse(wrongMethodRequest);

        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", wrongMethodResponse.getStatusLine());
    }
}

