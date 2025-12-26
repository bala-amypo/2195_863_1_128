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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl implements com.example.demo.service.AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;

    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapshotRepository,
                                         HoldingRecordRepository holdingRepository,
                                         AssetClassAllocationRuleRepository ruleRepository,
                                         RebalancingAlertRecordRepository alertRepository) {
        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepository.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor");
        }

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();

        // Group by asset class
        Map<AssetClassType, Double> valueByClass = holdings.stream()
                .collect(Collectors.groupingBy(
                        HoldingRecord::getAssetClass,
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)
                ));

        // Create simplistic JSON (can use Jackson but manual is enough for this simple requirement)
        String json = valueByClass.toString(); 

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId,
                LocalDateTime.now(),
                totalValue,
                json
        );
        
        // Process Alerts
        List<AssetClassAllocationRule> rules = ruleRepository.findByInvestorIdAndActiveTrue(investorId);
        for (AssetClassAllocationRule rule : rules) {
            double assetValue = valueByClass.getOrDefault(rule.getAssetClass(), 0.0);
            double currentPercentage = (assetValue / totalValue) * 100.0;
            
            if (currentPercentage > rule.getTargetPercentage()) {
                // Simplified severity logic
                AlertSeverity severity = AlertSeverity.LOW;
                double diff = currentPercentage - rule.getTargetPercentage();
                if (diff > 10.0) severity = AlertSeverity.HIGH;
                else if (diff > 5.0) severity = AlertSeverity.MEDIUM;
                
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId,
                        rule.getAssetClass(),
                        currentPercentage,
                        rule.getTargetPercentage(),
                        severity,
                        "Rebalancing needed: exceeded target",
                        LocalDateTime.now(),
                        false
                );
                alertRepository.save(alert);
            }
        }

        return snapshotRepository.save(snapshot);
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found with id: " + id));
    }

    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}
