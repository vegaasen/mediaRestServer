package com.vegaasen.fun.mediaserver.media.utils;

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

    private final static String EXP_MP3 = "(^.*(mp3))";

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
        final Set<File> foundMp3s = DriveUtils.findFiles(null, EXP_MP3);
        assertNotNull(foundMp3s);
        assertFalse(foundMp3s.isEmpty());
        assertTrue(foundMp3s.size() > 0);
    }

    @Test
    public void testFindFiles_mp3_specificScan() throws Exception {
        final Set<File> foundMp3s = DriveUtils.findFiles("/Users/vegardaasen/Music", EXP_MP3);
        assertNotNull(foundMp3s);
        assertFalse(foundMp3s.isEmpty());
        assertTrue(foundMp3s.size() > 0);
    }

    @Test
    public void testFindFile() throws Exception {

    }

}
