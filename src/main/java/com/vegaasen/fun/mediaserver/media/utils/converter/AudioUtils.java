package com.vegaasen.fun.mediaserver.media.utils.converter;

import com.google.common.base.Strings;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Utils that supports conversions of a File or String (path) to various types of Audio-files.
 * Flac, Ogg and M4a is not often used, Mp3 will be the no1 successor.
 *
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class AudioUtils {

    private AudioUtils() {
    }

    public final static class Mp3 {


        public static Mp3File convert(final File file) throws
                IOException,
                InvalidDataException,
                UnsupportedTagException {
            if (file == null) {
                throw new IllegalArgumentException("File cannot be null.");
            }
            if (file.exists()) {
                return convert(file.getAbsolutePath());
            }
            throw new FileNotFoundException("File expected to be there, but the file was not found.");
        }

        public static Mp3File convert (final String path) throws
                InvalidDataException,
                IOException,
                UnsupportedTagException {
            if (Strings.isNullOrEmpty(path)) {
                throw new IllegalArgumentException("Path cannot be null or empty.");
            }
            return new Mp3File(path);
        }

    }

    public static final class Wav {

    }

    public static final class M4a {

    }

    public static final class Ogg {

    }

    public static final class Flac {

    }

}
