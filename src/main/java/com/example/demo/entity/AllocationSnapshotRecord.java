package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
