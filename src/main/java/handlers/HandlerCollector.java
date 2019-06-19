package main.java.handlers;

import java.util.Arrays;
import java.util.List;

public class HandlerCollector {

    public static List<Handler> ALL_HANDLERS = Arrays.asList(
            new SimpleGetHandler(),
            new DefaultHandler(),
            new GetWithBodyHandler(),
            new MethodOptionsHandler(),
            new MethodOptions2Handler()
    );
}
