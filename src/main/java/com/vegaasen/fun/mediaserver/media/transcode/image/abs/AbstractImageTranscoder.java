package com.vegaasen.fun.mediaserver.media.transcode.image.abs;

import com.vegaasen.fun.mediaserver.media.model.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractImageTranscoder<T> {

    private T transcoder;
    private Class<T> clazz;

    protected AbstractImageTranscoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract T convert(Image t);

    public T convertToFormat(Image t) {
        return convert(t);
    }

    public T getTranscoder() {
        return transcoder;
    }

    public void setTranscoder(T type) {
        this.transcoder = type;
    }

    protected Image readify(Image other) {
        if (other == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }
        try {
            Image image = (Image) clazz.newInstance();
            image.setImage(other.getImage());
            image.copyTranslucentProperties(other);
            image.setImage(scaleAndRemaster(other));
            return image;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return other;
    }

    protected static synchronized BufferedImage scaleAndRemaster(Image image) {
        BufferedImage bufferedImage = image.getImage();
        if (bufferedImage != null) {
            Graphics2D graphics = bufferedImage.createGraphics();
            if (graphics != null) {
                graphics.scale(image.getX(), image.getY());
                graphics.setRenderingHints(image.getRenderingHints());
                graphics.drawImage(bufferedImage, null, 0, 0);
            }
            bufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), image.getImageType());
        }
        return bufferedImage;
    }

}
