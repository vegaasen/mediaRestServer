package com.vegaasen.fun.mediaserver.media.model.image.variant;

import com.vegaasen.fun.mediaserver.media.model.image.Image;
import com.vegaasen.fun.mediaserver.media.transcode.image.filter.BicubicFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class Png extends Image {

    public Png(BufferedImage image) {
        super();
        setImage(image);
    }

    public Png() {
        super();
    }

    @Override
    public void configure() {
        renderingHints = new HashMap<RenderingHints.Key, Object>();
        renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        filters.add(new BicubicFilter());
        imageType = BufferedImage.TYPE_INT_ARGB_PRE;
        setExtension("png");
    }
}
