package com.vegaasen.fun.mediaserver.media.utils;

import com.vegaasen.fun.mediaserver.media.common.RegExpTypes;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public class DriveUtilsTest {

    private static final String MY_TEST_PATH = "/Users/vegardaasen/tmp";

    @Before
    public void setUp() {

    }

    @Test
    public void testGetActiveDrives() throws Exception {
        final File[] diskRoot = DriveUtils.getActiveDrives();
        assertNotNull(diskRoot);
        assertTrue(diskRoot.length > 0);
    }

    @Test
    @Ignore("Ignored, takes too much memory+cpu, and too long time.")
    public void testFindFiles_mp3_fullScan() throws Exception {
        final Set<File> foundMp3s = DriveUtils.findFiles(null, RegExpTypes.Audio.MP3.getName());
        assertNotNull(foundMp3s);
        assertFalse(foundMp3s.isEmpty());
        assertTrue(foundMp3s.size() > 0);
    }

    @Test
    public void testFindFiles_mp3_specificScan() throws Exception {
        final Set<File> foundMp3s = DriveUtils.findFiles(MY_TEST_PATH, RegExpTypes.Audio.MP3.getName());
        assertNotNull(foundMp3s);
        assertFalse(foundMp3s.isEmpty());
        assertTrue(foundMp3s.size() > 0);
    }

    @Test
    public void testFindFiles_images_jpeg_specificScan() {
        final Set<File> foundImages = DriveUtils.findFiles(MY_TEST_PATH, RegExpTypes.Images.JPEG.getRegexp());
        assertNotNull(foundImages);
        assertFalse(foundImages.isEmpty());
        assertTrue(foundImages.size() > 1);
    }

    @Test
    public void testFindFile() throws Exception {

    }

}
