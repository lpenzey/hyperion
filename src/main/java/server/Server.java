package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Integer port;
    private boolean serverIsRunning = false;
    private Router router;

    public Server(Integer port, Router router) {
        this.port = port;
        this.router = router;
    }

    public void start() throws IOException {
        serverIsRunning = true;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening for connection on port " + port );
        while(serverIsRunning) {
            Socket clientSocket = serverSocket.accept();
            Client client = new Client(clientSocket);
            ServerRunner protocol = new ServerRunner(client, router);
            (new Thread(protocol)).start();
        }
        serverSocket.close();
    }

    private void stop() {
        serverIsRunning = false;
    }
}