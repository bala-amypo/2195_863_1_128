package com.example.demo.entity;

import com.example.demo.entity.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RebalancingAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentPercentage;
    private Double targetPercentage;

    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;

    private String message;
    private LocalDateTime createdAt;
    private Boolean resolved;

    public RebalancingAlertRecord() {}

    public RebalancingAlertRecord(Long investorId, AssetClassType assetClass,
                                  Double currentPercentage, Double targetPercentage,
                                  AlertSeverity severity, String message,
                                  LocalDateTime createdAt, Boolean resolved) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentPercentage = currentPercentage;
        this.targetPercentage = targetPercentage;
        this.severity = severity;
        this.message = message;
        this.createdAt = createdAt;
        this.resolved = resolved;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    public Double getCurrentPercentage() { return currentPercentage; }
    public AlertSeverity getSeverity() { return severity; }
}
