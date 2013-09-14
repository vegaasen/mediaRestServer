package com.vegaasen.fun.mediaserver.media.utils.converter;

import com.google.common.base.Strings;
import com.vegaasen.fun.mediaserver.media.utils.DriveUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Logger;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class ImageConverterUtils {

    private static final Logger LOG = Logger.getLogger(ImageConverterUtils.class.getName());

    private ImageConverterUtils() {
    }

    public static void convertToBufferedImage(final String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }
        convertToBufferedImage(DriveUtils.getFile(path));
    }

    public static void convertToBufferedImage(final File file) {
        if (DriveUtils.exists(file)) {
            try {
                convertToBufferedImage(new BufferedInputStream(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                LOG.warning(String.format("Unable to convert to inputstream. Why? /n%s", e.getMessage()));
            }
        }
    }

    public static void convertToBufferedImage(final InputStream stream) {
        if(stream==null){
            throw new IllegalArgumentException("Stream cannot be null");
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(stream);
            if(bufferedImage!=null) {
                return;
            }
        } catch (IOException e) {
            LOG.severe("Unable to convert stream to bufferedimage. Perhaps the source is not an image?");
        }
    }

    //todo: not String, change type -- just a placeholder..
    public static final class BMP extends ImageConvert<String> {

        @Override
        void convertToFormat(BufferedImage image) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
