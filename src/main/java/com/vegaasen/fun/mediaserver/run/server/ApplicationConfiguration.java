package com.vegaasen.fun.mediaserver.run.server;

import com.google.common.reflect.ClassPath;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Todo: make this work.
 *
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
@WebServlet(loadOnStartup = 1, displayName = "Rest Application")
@ApplicationPath("/")
final class ApplicationConfiguration extends Application {

    private static final Set<Class<?>> REST_CLASSES = new HashSet<Class<?>>();
    private static final String REST_PACKAGE_PATH = "com.vegaasen.fun.mediaserver.rest.controller";
    private static final Logger LOG = Logger.getLogger(ApplicationConfiguration.class.getName());

    static {
        try {
            final ClassPath classPath = ClassPath.from(ApplicationConfiguration.class.getClassLoader());
            if (classPath != null) {
                for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClasses(REST_PACKAGE_PATH)) {
                    REST_CLASSES.add(classInfo.getClass());
                }
            }
        } catch (IOException e) {
            LOG.severe("Unable to load REST-based classes. No services will be available. Reason: " + e.getMessage());
        }
    }

    public ApplicationConfiguration() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        if (REST_CLASSES.isEmpty()) {
            return Collections.emptySet();
        }
        return REST_CLASSES;
    }

}
