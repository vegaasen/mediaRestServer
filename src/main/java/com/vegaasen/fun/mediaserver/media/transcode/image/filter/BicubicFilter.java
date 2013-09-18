package com.vegaasen.fun.mediaserver.media.transcode.image.filter;

/**
 * @author <a href="vegard.aasen@gmail.com">vegardaasen</a>
 */
public final class BicubicFilter implements ImageFilter {

    final protected float a;

    public BicubicFilter() {
        a = -0.5f;
    }

    protected BicubicFilter(float a) {
        this.a = a;
    }

    public final float apply(float value) {
        if (value == 0)
            return 1.0f;
        if (value < 0.0f)
            value = -value;
        float vv = value * value;
        if (value < 1.0f) {
            return (a + 2f) * vv * value - (a + 3f) * vv + 1f;
        }
        if (value < 2.0f) {
            return a * vv * value - 5 * a * vv + 8 * a * value - 4 * a;
        }
        return 0.0f;
    }

    public float getSamplingRadius() {
        return 2.0f;
    }
}
