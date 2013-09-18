package com.vegaasen.fun.mediaserver.media.transcode.image.filter;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public interface ImageFilter {

    public float getSamplingRadius();

    float apply(float v);

}