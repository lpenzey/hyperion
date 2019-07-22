package main.java.server;

import main.java.application.Routes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Router router;
    private Integer port;
    private boolean serverIsRunning = true;
    private Routes routes;

    public Server(Integer port, Router router) {
        this.port = port;
        this.router = router;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening for connection...");
        while(serverIsRunning) {
            Socket clientSocket = serverSocket.accept();
            Client client = new Client(clientSocket);
            ProtocolRunner protocol = new ProtocolRunner(client, Routes.ROUTER);
            (new Thread(protocol)).start();
        }
        serverSocket.close();
    }

    private void stop() {
        serverIsRunning = false;
    }
}