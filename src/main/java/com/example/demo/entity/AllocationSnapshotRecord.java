package com.example.demo.entity;

import jakarta.persistence.*;


@Entity

public class AllocationSnapshotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId; // 3NF normalization

    private LocalDateTime snapshotDate;

    private Double totalPortfolioValue;

    @Lob
    private String allocationDetailsJson;

    public AllocationSnapshotRecord(Long investorId, LocalDateTime snapshotDate, Double totalPortfolioValue, String allocationDetailsJson) {
        this.investorId = investorId;
        this.snapshotDate = snapshotDate;
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationDetailsJson = allocationDetailsJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public Double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(Double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public String getAllocationDetailsJson() {
        return allocationDetailsJson;
    }

    public void setAllocationDetailsJson(String allocationDetailsJson) {
        this.allocationDetailsJson = allocationDetailsJson;
    }
}
