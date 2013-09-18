package com.vegaasen.fun.mediaserver.media.transcode.image;

import com.vegaasen.fun.mediaserver.media.model.image.Image;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Bitmap;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Jpeg;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Png;
import com.vegaasen.fun.mediaserver.media.transcode.image.abs.AbstractImageTranscoder;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class ImageTranscoder {

    public static final JPEG JPEG = new JPEG(Jpeg.class);
    public static final BMP BMP = new BMP(Bitmap.class);
    public static final PNG PNG = new PNG(Png.class);

    private ImageTranscoder() {
    }

    public static final class BMP extends AbstractImageTranscoder<Bitmap> {

        protected BMP(Class<Bitmap> clazz) {
            super(clazz);
        }

        @Override
        public Bitmap convert(final Image image) {
            if (eligibleForConvert(image)) {
                final Bitmap converted = (Bitmap) readify(image);
                if (converted != null) {
                    return converted;
                }
            }
            return null;
        }

    }

    public static final class JPEG extends AbstractImageTranscoder<Jpeg> {

        protected JPEG(Class<Jpeg> clazz) {
            super(clazz);
        }

        @Override
        public Jpeg convert(Image image) {
            if (eligibleForConvert(image)) {
                final Jpeg converted = (Jpeg) readify(image);
                if (converted != null) {
                    return converted;
                }
            }
            return null;
        }

    }

    public static final class PNG extends AbstractImageTranscoder<Png> {

        protected PNG(Class<Png> clazz) {
            super(clazz);
        }

        @Override
        public Png convert(Image image) {
            if (eligibleForConvert(image)) {
                final Png converted = (Png) readify(image);
                if (converted != null) {
                    return converted;
                }
            }
            return null;
        }

    }

    static boolean eligibleForConvert(final Image image) {
        return image != null && image.getImage() != null;
    }

}
