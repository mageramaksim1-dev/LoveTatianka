package hw.zako.zakohealthindicator.util;

public final class ColorUtil {
    private ColorUtil() {
    }

    public static int getColor(float health) {
        // Старі кольори залишені без змін
        if (health <= 5.0f) return 0xFFFF5555;   // червоний
        if (health <= 10.0f) return 0xFFFFAA00;  // помаранчевий
        if (health <= 15.0f) return 0xFFFFFF55;  // жовтий
        if (health <= 20.0f) return 0xFF55FF55;  // зелений

        // Нові кольори
        if (health <= 40.0f) return 0xFF00AA00;  // старий зелений
        if (health <= 60.0f) return 0xFFC084FC;  // світло-фіолетовий
        if (health <= 80.0f) return 0xFF66BFFF;  // голубий
        return 0xFF55D8FF;                       // блакитний
    }
}
