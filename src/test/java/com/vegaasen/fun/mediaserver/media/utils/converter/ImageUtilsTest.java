package com.vegaasen.fun.mediaserver.media.utils.converter;

import com.vegaasen.fun.mediaserver.media.model.image.Image;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Bitmap;
import com.vegaasen.fun.mediaserver.media.model.image.variant.Jpeg;
import com.vegaasen.fun.mediaserver.media.utils.DriveUtils;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import static junit.framework.Assert.*;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class ImageUtilsTest {

    public static final String PATH = "/images/me.jpg";
    private InputStream image;

    @Before
    public void setUp() {
        image = DriveUtils.getFileFromResource(PATH);
        assertNotNull(image);
    }

    @Test
    public void shouldConvertJpegToBmpUsingBuffImage() {
        BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(image);
        assertNotNull(bufferedImage);
        convertToBmpWithBufferedImage(bufferedImage);
    }

    @Test
    public void shouldConvertImageToBmpUsingFile() {
        BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(PATH);
        assertNotNull(bufferedImage);
        convertToBmpWithBufferedImage(bufferedImage);
    }

    @Test
    public void shouldConvertJpegToPngUsingBuffImage() {
        BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(image);
        assertNotNull(bufferedImage);
        convertToBmpWithBufferedImage(bufferedImage);
    }

    @Test
    public void shouldConvertImageToPngUsingFile() {
        BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(PATH);
        assertNotNull(bufferedImage);
        convertToBmpWithBufferedImage(bufferedImage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConvert_usingNull() {
        ImageUtils.convertToBufferedImage((File) null);
    }

    @Test
    public void shouldConvertToImage() {
        Image im = ImageUtils.getAsImage(ImageUtils.convertToBufferedImage(image), PATH);
        assertNotNull(im);
        assertTrue(im instanceof Jpeg);
    }

    @Test
    public void shouldGetAsImageAndConvertToBmp() {
        final BufferedImage iiiimage = ImageUtils.convertToBufferedImage(image);
        final Image im = ImageUtils.getAsImage(iiiimage, PATH);
        assertNotNull(im);
        assertNotNull(im.getImage());
        assertSame(iiiimage, im.getImage());
        assertTrue(im instanceof Jpeg);
        final Image bmpIm = ImageUtils.BMP.convertImage(im);
        assertNotNull(bmpIm);
        assertNotSame(im, bmpIm);
        assertNotSame(im.getImage(), bmpIm.getImage());
        assertNotSame(bmpIm.getImage(), iiiimage);
        assertTrue(bmpIm instanceof Bitmap);
    }

    private void convertToBmpWithBufferedImage(BufferedImage bufferedImage) {
        assertNotNull(bufferedImage);
        Image jpeg = new Jpeg();
        jpeg.setImage(bufferedImage);
        Image bmp = ImageUtils.BMP.convertImage(jpeg);
        assertNotNull(bmp);
        assertNotNull(bmp.getImage());
        assertNotSame(jpeg.getImage(), bmp.getImage());
        assertEquals("bmp", bmp.getExtension());
    }

    private void convertToPngWithBufferedImage(BufferedImage bufferedImage) {
        assertNotNull(bufferedImage);
        Image jpeg = new Jpeg();
        jpeg.setImage(bufferedImage);
        Image png = ImageUtils.PNG.convertImage(jpeg);
        assertNotNull(png);
        assertNotNull(png.getImage());
        assertNotSame(jpeg.getImage(), png.getImage());
        assertEquals("png", png.getExtension());
    }

}
