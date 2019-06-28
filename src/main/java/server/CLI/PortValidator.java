package main.java.server.CLI;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import main.java.server.ServerLogger;

import java.util.logging.Level;

public class PortValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        int n = 0;

        try {
            n = Integer.parseInt(value);
        } catch (Exception e) {
            ServerLogger.serverLogger.log(Level.FINE, "Error: " + e);
        }

        if (n < 1024 || n > 60000)
            throw new ParameterException("Parameter " + name + " should be a number between 1024 and 60000 (found " + value + ")");
        }
}
