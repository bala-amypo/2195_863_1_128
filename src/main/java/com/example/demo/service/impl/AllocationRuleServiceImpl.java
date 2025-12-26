package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        return rule; // minimal implementation
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule) {
        return rule; // minimal implementation
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return Collections.emptyList();
    }
}
