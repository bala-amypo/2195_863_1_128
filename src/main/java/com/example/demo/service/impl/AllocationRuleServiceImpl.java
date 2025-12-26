package com.example.demo.service.impl;

import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

@Service   // 🔴 THIS WAS MISSING
public class AllocationRuleServiceImpl implements AllocationRuleService {

    @Override
    public boolean isValid(double value) {
        return value >= 0 && value <= 100;
    }
}
