package com.vegaasen.fun.mediaserver.run.server;

import com.google.common.base.Strings;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;

import java.io.IOException;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractController {

    public abstract void perform(Action action);

    abstract void start();

    abstract void stop();

    abstract Connector[] getAllConnectors();

    abstract Handler getHandler() throws IOException;

    public enum Action {
        START, STOP, INITIATE, COUNT, UNKNOWN;

        public static Action getAction(String action) {
            if (!Strings.isNullOrEmpty(action)) {
                for (Action a : values()) {
                    if (a.name().toLowerCase().equals(action.toLowerCase())) {
                        return a;
                    }
                }
            }
            return UNKNOWN;
        }
    }

}
