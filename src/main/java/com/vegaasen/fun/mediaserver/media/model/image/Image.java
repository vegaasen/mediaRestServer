package com.vegaasen.fun.mediaserver.media.model.image;

import com.vegaasen.fun.mediaserver.media.common.RegTypes;
import com.vegaasen.fun.mediaserver.media.model.abs.AbstractMedia;
import com.vegaasen.fun.mediaserver.media.transcode.image.filter.ImageFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class Image extends AbstractMedia {

    private long x;
    private long y;
    private long loaded;
    protected int imageType;
    private String extension;
    private BufferedImage image;
    protected Map<RenderingHints.Key, Object> renderingHints;
    protected Set<ImageFilter> filters = new HashSet<ImageFilter>();
    private RegTypes.Images to;

    public abstract void configure();

    public Image() {
        configure();
        setLoaded(System.currentTimeMillis());
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public int getImageType() {
        return imageType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getLoaded() {
        return loaded;
    }

    public void setLoaded(long loaded) {
        this.loaded = loaded;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Map<RenderingHints.Key, Object> getRenderingHints() {
        return renderingHints;
    }

    public Set<ImageFilter> getFilters() {
        return filters;
    }

    public void copyTranslucentProperties(final Image image) {
        this.setX(image.getX());
        this.setY(image.getY());
        this.setLoaded(image.getLoaded());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        return loaded == image1.loaded &&
                x == image1.x &&
                y == image1.y &&
                !(image != null ? !image.equals(image1.image) : image1.image != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (loaded ^ (loaded >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
