package test.java.server;

import main.java.server.Client;
import main.java.server.Router;
import main.java.server.ServerRunner;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ServerRunnerTest {
    private Router router;
    private Client client;
    private SocketStub socketStub;

    private String runSessionAndRetrieveResponse(String request) throws IOException {
        socketStub = new SocketStub(request);
        client = new Client(socketStub);
        ServerRunner serverRunner = new ServerRunner(client, router);
        serverRunner.run();

        return socketStub.printSentData();
    }

    @Before
    public void setUp() throws IOException {
        router = new Router();
        router.get("/simple_get", RouterTest.HandlersStub.SimpleGet);
    }

    @Test
    public void canCreateResponseForRequestForRecognizedRoute() throws IOException {

        String request = "GET /simple_get HTTP/1.1";
        String response = runSessionAndRetrieveResponse(request);
        assertEquals("HTTP/1.1 200 OK\r\nAllow:HEAD,GET,OPTIONS\r\n\r\n", response);
    }

    @Test
    public void canCreateResponseForRequestForUnrecognizedRoute() throws IOException {

        String request = "GET /nonexistant_endpoint HTTP/1.1";
        String response = runSessionAndRetrieveResponse(request);
        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n\r\n", response);
    }

    @Test
    public void canCreateResponseForRequestForMethodNotAllowedRoute() throws IOException {

        String request = "POST /simple_get HTTP/1.1";
        String response = runSessionAndRetrieveResponse(request);
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\nAllow:GET\r\n\r\n", response);
    }
}
