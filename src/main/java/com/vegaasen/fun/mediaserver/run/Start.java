package com.vegaasen.fun.mediaserver.run;

import com.vegaasen.fun.mediaserver.run.server.AbstractController;
import com.vegaasen.fun.mediaserver.run.server.JettyController;

import java.util.logging.Logger;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class Start {

    private static final Logger LOG = Logger.getLogger(Start.class.getName());

    public static void main(String... args) {
        if(args!=null) {
            if(args.length>1) {
                LOG.severe(String.format("%s\n%s", "Too many arguments. Requires one.", "Usage: Start [start|stop]"));
            }
            JettyController.getInstance().perform(AbstractController.Action.getAction(args[0]));
        }
        System.exit(-1);
    }

}
