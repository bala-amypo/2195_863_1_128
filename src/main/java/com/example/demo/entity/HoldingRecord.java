package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;

    private LocalDateTime updatedTime;

    public HoldingRecord(Long investorId, AssetClassType assetClass, Double currentValue, LocalDateTime updatedTime) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.updatedTime = updatedTime;
    }
}
