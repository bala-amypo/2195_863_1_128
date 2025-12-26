package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import java.time.LocalDateTime;
import java.util.*;

public class AllocationSnapshotServiceImpl {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRuleRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo) {

        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty())
            throw new IllegalArgumentException("No holdings");

        double total = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        AllocationSnapshotRecord snap =
                new AllocationSnapshotRecord(investorId, LocalDateTime.now(), total, "{}");
        snapshotRepo.save(snap);
        return snap;
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found " + id));
    }

    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
