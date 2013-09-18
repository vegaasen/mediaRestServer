package com.vegaasen.fun.mediaserver.media.utils.converter.abs;

import com.vegaasen.fun.mediaserver.media.common.RegTypes;
import com.vegaasen.fun.mediaserver.media.model.image.Image;

import java.awt.image.BufferedImage;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public interface ImageConvert {

    public Image convertImage(Image image);

    public void setImageType(RegTypes.Images imageType);

}
