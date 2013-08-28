package com.vegaasen.fun.mediaserver.run.server;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class JettyController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(JettyController.class.getName());
    private static final String CONTEXT_ROOT = "/";
    private static final String urlCharacterSet = "UTF-8";

    private static Server server;
    private static JettyController jettyController;

    private JettyController() {
    }

    public static JettyController getInstance() {
        if (jettyController == null) {
            jettyController = new JettyController();
        }
        return jettyController;
    }

    public void perform(Action action) {
        switch (action) {
            case START:
                start();
                break;
            case STOP:
                stop();
                break;
            default:
                LOG.warning("Invalid action. Choose between ...");
                break;
        }
    }

    void start() {
        server = new Server();
        setProperties();
        try {
            server.setConnectors(getAllConnectors());
            server.setHandler(getHandler());
            server.setStopAtShutdown(true);
            server.setDumpBeforeStop(true);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }

    void stop() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.warning("Unable to stop running server.");
                server.destroy();
            }
        }
    }

    Handler getHandler() throws IOException {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setClassLoader(new WebAppClassLoader(webAppContext));
        webAppContext.setContextPath(CONTEXT_ROOT);
        webAppContext.setBaseResource(Resource.newClassPathResource("/webapp/", true, true));
        webAppContext.setConfigurations(
                new Configuration[]{
                        new AnnotationConfiguration(),
                        new WebXmlConfiguration(),
                        new WebInfConfiguration(),
                        new PlusConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration()});
        return webAppContext;
    }

    Connector[] getAllConnectors() {
        if (server != null) {
            Connector[] connectors = new Connector[1];
            ServerConnector httpConnector = new ServerConnector(server);
            httpConnector.setHost("localhost");
            httpConnector.setIdleTimeout(0L);
            httpConnector.setStopTimeout(0L);
            httpConnector.setName("Default Controller");
            httpConnector.setPort(7000);
            connectors[0] = httpConnector;
            return connectors;
        }
        return null;
    }

    private void setProperties() {
        System.setProperty("org.mortbay.util.URI.charset", urlCharacterSet);
        System.setProperty("org.mortbay.util.UrlEncoding.charset", urlCharacterSet);
    }

}
