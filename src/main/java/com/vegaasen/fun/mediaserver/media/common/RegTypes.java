package com.vegaasen.fun.mediaserver.media.common;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public enum RegTypes {

    INSTANCE;

    public enum Images {

        JPEG("(^.*(JPG|jpg|JPEG|jpeg))"), GIF(""), PNG(""), TIFF(""), BMP("");

        private String regexp;

        private Images(String n) {
            regexp = n;
        }

        public String getRegexp() {
            return regexp;
        }

        public static Images getImageByRegexp(final String s) {
            if (s != null) {
                for (Images i : values()) {
                    if (s.matches(i.getRegexp())) {
                        return i;
                    }
                }
            }
            return null;
        }

    }

    public enum Audio {

        MP3("(^.*(mp3))"), WMA(""), MP4(""), OGG("");

        private String regexp;

        private Audio(String n) {
            regexp = n;
        }

        public String getRegexp() {
            return this.regexp;
        }

        public static Audio getAudioByRegexp(final String s) {
            if (s != null) {
                for (Audio i : values()) {
                    if (s.matches(i.getRegexp())) {
                        return i;
                    }
                }
            }
            return null;
        }

    }

}
