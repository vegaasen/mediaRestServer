package com.vegaasen.fun.mediaserver.media.common;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public enum  RegExpTypes {

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

    }

    public enum Audio {

        MP3("(^.*(mp3))"), WMA(""), MP4(""), OGG("");

        private String name;

        private Audio(String n) {
            name = n;
        }

        public String getName() {
            return this.name;
        }

    }

}
