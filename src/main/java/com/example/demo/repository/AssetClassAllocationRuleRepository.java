package com.example.demo.repository;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);
    List<AssetClassAllocationRule> findByInvestorIdAndActiveTrue(Long investorId);

    // mocked HQL
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorId);
}
