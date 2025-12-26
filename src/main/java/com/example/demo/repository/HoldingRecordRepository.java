package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorId(Long investorId);

    // HQL for "value greater than" test
    @Query("SELECT h FROM HoldingRecord h WHERE h.currentValue > :val")
    List<HoldingRecord> findByValueGreaterThan(@Param("val") Double value);

    // HQL for "investor and asset class" test
    @Query("SELECT h FROM HoldingRecord h WHERE h.investorId = :invId AND h.assetClass = :ac")
    List<HoldingRecord> findByInvestorAndAssetClass(@Param("invId") Long investorId, @Param("ac") AssetClassType assetClass);
}
