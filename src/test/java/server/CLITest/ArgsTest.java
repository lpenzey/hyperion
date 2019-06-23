package test.java.server.CLITest;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.server.CLI.Args;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsTest {
    private Args commandLineParser;
    private JCommander jc;

    @Before
    public void createTestObjects() {
        commandLineParser = new Args();
        jc = new JCommander(commandLineParser);
    }

    @Test
    public void parsesPortNumberArgument()  {
        String[] argv = { "-port=5000"};
        jc.parse(argv);

        assertEquals(commandLineParser.getPort().intValue(), 5000);
    }

    @Test
    public void throwsExceptionIfPortIsUnder1024()  {
        try {
            String[] argv = {"-port=1023"};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("Parameter -port should be a number between 1024 and 49151 (found 1023)", e.getMessage());
        }
    }

    @Test
    public void throwsExceptionIfPortIsOver49151()  {
        try {
            String[] argv = {"-port=49152"};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("Parameter -port should be a number between 1024 and 49151 (found 49152)", e.getMessage());
        }
    }

    @Test
    public void showsUsageWhenHelpArgumentGiven()  {
        try {
            String[] argv = {"--help"};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("The following option is required: [--port | -port | -p]", e.getMessage());
        }
    }

    @Test
    public void displaysRequiredParameterWhenNoArgumentsGiven()  {
        try {
            String[] argv = {};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("The following option is required: [--port | -port | -p]", e.getMessage());
        }
    }

    @Test
    public void throwsExceptionWhenNonIntegerArgumentGiven()  {
        try {
            String[] argv = {"-port=hello"};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("Parameter -port should be a number between 1024 and 49151 (found hello)", e.getMessage());
        }
    }

    @Test
    public void throwsExceptionWhenNullArgumentGiven()  {
        try {
            String[] argv = {"null"};
            jc.parse(argv);
            fail();
        } catch (ParameterException e) {
            assertEquals("Was passed main parameter 'null' but no main parameter was defined in your arg class", e.getMessage());
        }
    }
}
