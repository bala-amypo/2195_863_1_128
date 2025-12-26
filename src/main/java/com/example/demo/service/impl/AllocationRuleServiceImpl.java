package com.example.demo.service.impl;



import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import java.util.*;

public class AllocationRuleServiceImpl {

    private final AssetClassAllocationRuleRepository repo;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repo) {
        this.repo = repo;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100)
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        return repo.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule data) {
        AssetClassAllocationRule rule = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found " + id));
        rule.setTargetPercentage(data.getTargetPercentage());
        return repo.save(rule);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}




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
