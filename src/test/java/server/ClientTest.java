package test.java.server;

import main.java.server.Client;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    private Client client;
    private SocketStub socketStub;

    @Before
    public void createClientWithSocketStub() throws IOException {
        socketStub = new SocketStub("message");
        client = new Client(socketStub);
    }

    @Test
    public void canReadFromSocket() throws IOException {
        BufferedReader reader = client.getReader();
        assertEquals("message", reader.readLine());
    }

    @Test
    public void canWriteToSocket() {
        PrintWriter writer = client.getWriter();
        writer.println("message");
        assertEquals("message\n", socketStub.getOutputStream().toString());
    }

    @Test
    public void canCloseSocketConnection() {
        client.close();

        assertTrue(socketStub.isClosed());
    }
}
