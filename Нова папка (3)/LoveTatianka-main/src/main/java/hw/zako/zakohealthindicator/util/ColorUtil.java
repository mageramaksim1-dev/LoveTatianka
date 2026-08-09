package hw.zako.zakohealthindicator.util;

public final class ColorUtil {
    private ColorUtil() {
    }

    public static int getColor(float health) {
        if (health <= 5.0f) return 0xFFFF5555;
        if (health <= 10.0f) return 0xFFFFAA00;
        if (health <= 15.0f) return 0xFFFFFF55;
        if (health <= 20.0f) return 0xFF55FF55;
        return 0xFF00AA00;
    }
}
