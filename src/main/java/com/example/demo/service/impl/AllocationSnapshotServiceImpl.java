package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.AllocationSnapshotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapshotRepository,
                                         HoldingRecordRepository holdingRepository,
                                         AssetClassAllocationRuleRepository ruleRepository,
                                         RebalancingAlertRecordRepository alertRepository) {
        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    @Transactional
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepository.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor: " + investorId);
        }

        double totalPortfolioValue = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        // Compute per-asset value
        Map<AssetClassType, Double> assetValues = new HashMap<>();
        for (HoldingRecord holding : holdings) {
            assetValues.merge(holding.getAssetClass(), holding.getCurrentValue(), Double::sum);
        }

        // Compute percentages and build JSON
        Map<String, Double> allocationMap = new HashMap<>();
        Map<AssetClassType, Double> percentageMap = new HashMap<>();

        for (Map.Entry<AssetClassType, Double> entry : assetValues.entrySet()) {
            double percentage = (entry.getValue() / totalPortfolioValue) * 100.0;
            allocationMap.put(entry.getKey().name(), percentage);
            percentageMap.put(entry.getKey(), percentage);
        }

        String allocationJson;
        try {
            allocationJson = objectMapper.writeValueAsString(allocationMap);
        } catch (Exception e) {
            throw new RuntimeException("Error converting allocation to JSON", e);
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId,
                LocalDateTime.now(),
                totalPortfolioValue,
                allocationJson
        );
        snapshot = snapshotRepository.save(snapshot);

        // Check rules and create alerts
        List<AssetClassAllocationRule> rules = ruleRepository.findActiveRulesHql(investorId);
        for (AssetClassAllocationRule rule : rules) {
            Double currentPercentage = percentageMap.getOrDefault(rule.getAssetClass(), 0.0);
            if (currentPercentage > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId,
                        rule.getAssetClass(),
                        currentPercentage,
                        rule.getTargetPercentage(),
                        AlertSeverity.HIGH,
                        "Asset class " + rule.getAssetClass() + " exceeds target percentage.",
                        LocalDateTime.now()
                );
                alertRepository.save(alert);
            }
        }

        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        // We need to implement lookup. Repository didn't have specific method but we can add or filtering manually?
        // Prompt didn't specify repository method 'findByInvestorId' for AllocationSnapshotRecordRepository.
        // Prompt said: AllocationSnapshotRecordRepository - JpaRepository<AllocationSnapshotRecord, Long> ONLY.
        // But Service said: getSnapshotsByInvestor.
        // I should probably use Example or just findAll and filter (inefficient) or add the method to repo if I could (but I already generated it).
        // Wait, I generated AllocationSnapshotRecordRepository with NO extra methods as per prompt.
        // To strictly follow "Generate ONLY... rules above" and the SERVICE requirement "getSnapshotsByInvestor", there is a mismatch.
        // BUT, I can rely on JpaRepository magic methods if I hadn't declared them? No, they must be in interface.
        // Actually, I can use `findAll()` and filter in stream since I can't edit the file easily without "multi_replace" but the prompt implied generating them.
        // I will use `findAll` and steam filter to adhere to the strict "Repository only has JpaRepository" rule provided (it listed specific methods for others, none for this).
        // OR I can use `Example` matcher.
        
        // However, usually "JpaRepository" implies standard methods are available, but `findByInvestorId` needs to be defined to be used.
        // Since I already wrote the file, I will use findAll -> filter.
        
        return snapshotRepository.findAll().stream()
                .filter(s -> s.getInvestorId().equals(investorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}
