package com.vegaasen.fun.mediaserver.media.model.rule;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class Rule {

    private String id;
    private String name;
    private boolean loaded;
    private RulePath path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public RulePath getPath() {
        return path;
    }

    public void setPath(RulePath path) {
        this.path = path;
    }
}
