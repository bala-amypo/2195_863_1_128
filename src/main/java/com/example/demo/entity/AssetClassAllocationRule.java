package com.example.demo.entity;
import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
@Entity 
@Table(name = "asset_class_allocation_rules")
public class AssetClassAllocationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long investorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;

    @Column(nullable = false)
    private Double targetPercentage;

    @Column(nullable = false)
    private Boolean active = true;

    public AssetClassAllocationRule(){
        this.active = true;
    }
    public AssetClassAllocationRule(Long investorId,AssetClassType assetClass, Double targetPercentage){
        setTargetPercentage(targetPercentage);
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.active = true;

    }
    public void setTargetPercentage(Double targetPercentage){
        if(targetPercentage == null || targetPercentage < 0 || targetPercentage > 100);
    }
    this.targetP
}