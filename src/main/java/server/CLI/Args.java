package main.java.server.CLI;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = { "--port", "-port", "-p" }, required = true, validateWith = PortValidator.class)
    private Integer port;

    @Parameter(names = { "--help", "-help", "-h"})
    private boolean help;

    public Integer getPort() {
        return port;
    }
}


