package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AllocationSnapshotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;
    private LocalDateTime snapshotDate;
    private Double totalPortfolioValue;

    @Column(columnDefinition = "TEXT")
    private String allocationJson;

    public AllocationSnapshotRecord() {}

    public AllocationSnapshotRecord(Long investorId, LocalDateTime snapshotDate,
                                    Double totalPortfolioValue, String allocationJson) {
        this.investorId = investorId;
        this.snapshotDate = snapshotDate;
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationJson = allocationJson;
    }

    // getters
    public Long getId() { return id; }
    public Long getInvestorId() { return investorId; }
    public LocalDateTime getSnapshotDate() { return snapshotDate; }
    public Double getTotalPortfolioValue() { return totalPortfolioValue; }
}
