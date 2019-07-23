package main.java.application;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.server.CLI.Args;
import main.java.server.Server;
import main.java.server.ServerLogger;

import java.io.IOException;
import java.util.logging.Level;

public class Application {

    public static void main(String[] args) {
        Args clArgs = new Args();
        JCommander jcParser = new JCommander(clArgs);

        try {
            jcParser.parse(args);
            Server server = new Server(clArgs.getPort(), Routes.ROUTER);
            server.start();
        } catch (ParameterException | IOException e) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + e.getMessage());
        }
    }
}
