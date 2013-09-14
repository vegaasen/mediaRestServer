package com.vegaasen.fun.mediaserver.media.utils.converter;

import java.awt.image.BufferedImage;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
abstract class ImageConvert<T> {

    abstract void convertToFormat(BufferedImage image);

    boolean eligibleForConvert(BufferedImage image) {
        return false;
    }

}
