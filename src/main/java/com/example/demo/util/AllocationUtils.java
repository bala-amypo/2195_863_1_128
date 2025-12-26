package com.example.demo.util;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;

public class AllocationUtils {
    
    // Utility class for common allocation logic or helper methods.
    // Currently empty as specific utility logic wasn't defined in the requirements,
    // but the file structure was requested.
    
    public static boolean isValidPercentage(Double percentage) {
        return percentage != null && percentage >= 0 && percentage <= 100;
    }
}
