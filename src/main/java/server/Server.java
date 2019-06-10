package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

class Server {
    private final Integer port;
    private boolean serverIsRunning = true;

    private Server(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
            int port = Integer.parseInt(args[0]);
            try {Server server = new Server(port);
                server.start();
            } catch (IOException error) {
                ServerLogger.serverLogger.log(Level.SEVERE, "Error: " + error);
        }

    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while(serverIsRunning) {
            Socket clientSocket = serverSocket.accept();
            Client client = new Client(clientSocket);
            ProtocolRunner protocol = new ProtocolRunner(client);
            (new Thread(protocol)).start();
        }
        serverSocket.close();
    }

    private void stop() {
        serverIsRunning = false;
    }
}