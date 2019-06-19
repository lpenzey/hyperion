package main.java.server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.server.CLI.Args;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final Integer port;
    private boolean serverIsRunning = true;

    private Server(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) {
            Args clArgs = new Args();
            JCommander jcParser = new JCommander(clArgs);

            try {
                jcParser.parse(args);
                Server server = new Server(clArgs.getPort());
                server.start();
            } catch (ParameterException | IOException e) {
                System.out.println(e.getMessage());
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