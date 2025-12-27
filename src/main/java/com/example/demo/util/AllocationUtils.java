package com.example.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AllocationUtils {

    private AllocationUtils() {
        // Private constructor to hide the implicit public one
    }

    public static double calculatePercentage(double part, double total) {
        if (total == 0) {
            return 0.0;
        }
        double percentage = (part / total) * 100.0;
        return round(percentage, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
