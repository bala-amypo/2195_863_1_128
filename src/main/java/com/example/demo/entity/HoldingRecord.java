package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HoldingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;
    
    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;
    
    private Double currentValue;
    private LocalDateTime timestamp;

    public HoldingRecord() {}

    public HoldingRecord(Long id, Long investorId, AssetClassType assetClass, Double currentValue, LocalDateTime timestamp) {
        this.id = id;
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.timestamp = timestamp;
    }

    public HoldingRecord(Long investorId, AssetClassType assetClass, Double currentValue, LocalDateTime timestamp) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }
    
    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }
    
    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double currentValue) { this.currentValue = currentValue; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
