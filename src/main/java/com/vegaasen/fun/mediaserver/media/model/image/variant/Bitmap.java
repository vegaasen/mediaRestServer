package com.vegaasen.fun.mediaserver.media.model.image.variant;

import com.vegaasen.fun.mediaserver.media.model.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class Bitmap extends Image {

    public Bitmap(BufferedImage image) {
        super();
        setImage(image);
    }

    public Bitmap() {
        super();
    }

    @Override
    public void configure() {
        renderingHints = new HashMap<RenderingHints.Key, Object>();
        renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        imageType = BufferedImage.TYPE_INT_RGB;
        setExtension("bmp");
    }
}
