package main.java.server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.application.App;
import main.java.application.Routes;
import main.java.server.CLI.Args;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private App app;
    private Integer port;
    private boolean serverIsRunning = true;

    public Server(Integer port, App app) {
        this.port = port;
        this.app = app;
    }

    public static void main(String[] args) {
            Args clArgs = new Args();
            JCommander jcParser = new JCommander(clArgs);
            App app = new App(new Routes());

            try {
                jcParser.parse(args);
                Server server = new Server(clArgs.getPort(), app);
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
            ProtocolRunner protocol = new ProtocolRunner(client, app);
            (new Thread(protocol)).start();
        }
        serverSocket.close();
    }

    private void stop() {
        serverIsRunning = false;
    }
}