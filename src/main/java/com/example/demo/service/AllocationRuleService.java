package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl {

    private final AssetClassAllocationRuleRepository allocationRuleRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository allocationRuleRepository) {
        this.allocationRuleRepository = allocationRuleRepository;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        return allocationRuleRepository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
        AssetClassAllocationRule existingRule = allocationRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
        
        if (updatedRule.getTargetPercentage() < 0 || updatedRule.getTargetPercentage() > 100) {
           throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }

        existingRule.setTargetPercentage(updatedRule.getTargetPercentage());
        existingRule.setAssetClass(updatedRule.getAssetClass());
        existingRule.setActive(updatedRule.getActive());
        
        return allocationRuleRepository.save(existingRule);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return allocationRuleRepository.findByInvestorId(investorId);
    }
}
