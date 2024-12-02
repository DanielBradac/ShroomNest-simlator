package cz.bradacd.shroomnest_simulator.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
    public static double roundToTwoDecimals(double value) {
        // Using BigDecimal for precise rounding
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
