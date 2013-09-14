package com.vegaasen.fun.mediaserver.media.model.rule;

import java.net.URL;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class RulePath {

    private boolean verified = false;
    private URL path;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public URL getPath() {
        return path;
    }

    public void setPath(URL path) {
        this.path = path;
    }
}
