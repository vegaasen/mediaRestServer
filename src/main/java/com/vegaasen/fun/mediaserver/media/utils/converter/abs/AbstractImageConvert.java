package com.vegaasen.fun.mediaserver.media.utils.converter.abs;

import com.vegaasen.fun.mediaserver.media.common.RegTypes;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractImageConvert implements ImageConvert {

    private static RegTypes.Images type;

    public void setImageType(RegTypes.Images type) {
        AbstractImageConvert.type = type;
    }

}
