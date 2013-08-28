package com.vegaasen.fun.mediaserver.media.model.abs;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractMedia<T> {

    private String filePath;
    private String fileName;
    private boolean executable;
    private long created;
    private long modified;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isExecutable() {
        return executable;
    }

    public void setExecutable(boolean executable) {
        this.executable = executable;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }
}
