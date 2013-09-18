package com.vegaasen.fun.mediaserver.media.utils.converter;

import com.google.common.base.Strings;
import com.vegaasen.fun.mediaserver.media.common.RegTypes;
import com.vegaasen.fun.mediaserver.media.model.image.Image;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Bitmap;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Jpeg;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Png;
import com.vegaasen.fun.mediaserver.media.transcode.image.ImageTranscoder;
import com.vegaasen.fun.mediaserver.media.utils.DriveUtils;
import com.vegaasen.fun.mediaserver.media.utils.converter.abs.AbstractImageConvert;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class ImageUtils {

    private static final Logger LOG = Logger.getLogger(ImageUtils.class.getName());

    public static final JPEG JPEG = new JPEG();
    public static final PNG PNG = new PNG();
    public static final BMP BMP = new BMP();

    private static final UnsharpenMask DEFAULT_MASK = UnsharpenMask.None;

    private ImageUtils() {
    }

    public static Image getAsImage(BufferedImage bufferedImage, final String file) {
        if (file == null || bufferedImage == null) {
            throw new IllegalArgumentException("Either filename/path nor bufferedimage can be null.");
        }
        Image image = null;
        switch (RegTypes.Images.getImageByRegexp(file)) {
            case JPEG:
                image = new Jpeg(bufferedImage);
                break;
            case BMP:
                image = new Bitmap(bufferedImage);
                break;
            case PNG:
                image = new Png(bufferedImage);
        }
        return image;
    }

    public static BufferedImage convertToBufferedImage(final String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }
        File file = DriveUtils.getFile(path);
        if (file == null || !file.exists()) {
            LOG.info("Did not find file on path. Using classpath instead.");
            return convertToBufferedImage(DriveUtils.getFileFromResource(path));
        }
        return convertToBufferedImage(file);
    }

    public static BufferedImage convertToBufferedImage(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("Expected a file, but found null.");
        }
        if (DriveUtils.exists(file)) {
            try {
                return convertToBufferedImage(new BufferedInputStream(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                LOG.warning(String.format("Unable to convert to inputstream. Why? /n%s", e.getMessage()));
            }
        }
        return null;
    }

    public static BufferedImage convertToBufferedImage(final InputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream cannot be null");
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(stream);
            if (bufferedImage != null) {
                return bufferedImage;
            }
        } catch (IOException e) {
            LOG.severe("Unable to convert stream to bufferedimage. Perhaps the source is not an image?");
        }
        return null;
    }

    public static ByteArrayOutputStream convertToOutputStream(final Image image) {
        if (image != null && image.getImage() != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                ImageIO.write(image.getImage(), image.getExtension(), outputStream);
                if (outputStream.size() > 0) {
                    return outputStream;
                }
            } catch (IOException e) {
                LOG.severe(String.format("Unable to write image to outputstream. Reason: %s", e.getMessage()));
            }
        }
        return null;
    }

    public static byte[] copyJpegMetaData(byte[] source, byte[] dest) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream out = new MemoryCacheImageOutputStream(baos);
        copyJpegMetaData(new ByteArrayInputStream(source), new ByteArrayInputStream(dest), out);
        return baos.toByteArray();
    }

    public static void copyJpegMetaData(InputStream source, InputStream dest, ImageOutputStream out) throws IOException {
        Iterator iter = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) iter.next();
        ImageInputStream iis = new MemoryCacheImageInputStream(source);
        reader.setInput(iis);
        IIOMetadata metadata = reader.getImageMetadata(0);
        iis.close();
        ImageInputStream outIis = new MemoryCacheImageInputStream(dest);
        reader.setInput(outIis);
        IIOImage image = reader.readAll(0, null);
        image.setMetadata(metadata);
        outIis.close();
        iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = (ImageWriter) iter.next();
        writer.setOutput(out);
        writer.write(image);
    }

    public static enum UnsharpenMask {
        None(0),
        Soft(0.15f),
        Normal(0.3f),
        VerySharp(0.45f),
        Oversharpened(0.60f);

        private final float factor;

        UnsharpenMask(float factor) {
            this.factor = factor;
        }

    }

    public static final class JPEG extends AbstractImageConvert {
        @Override
        public Image convertImage(Image image) {
            return ImageTranscoder.JPEG.convert(image);
        }
    }

    public static final class BMP extends AbstractImageConvert {
        @Override
        public Image convertImage(Image image) {
            return ImageTranscoder.BMP.convert(image);
        }
    }

    public static final class PNG extends AbstractImageConvert {
        @Override
        public Image convertImage(Image image) {
            return ImageTranscoder.PNG.convert(image);
        }
    }

}
