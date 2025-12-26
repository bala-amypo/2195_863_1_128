package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import java.util.List;

public interface AllocationRuleService {

    List<AllocationRule> getRulesByInvestor(Long investorId);
}
