package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoldingRecordServiceImpl implements com.example.demo.service.HoldingRecordService {

    private final HoldingRecordRepository holdingRecordRepository;

    public HoldingRecordServiceImpl(HoldingRecordRepository holdingRecordRepository) {
        this.holdingRecordRepository = holdingRecordRepository;
    }

    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() <= 0) {
            throw new IllegalArgumentException("Value must be > 0");
        }
        return holdingRecordRepository.save(holding);
    }

    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return holdingRecordRepository.findByInvestorId(investorId);
    }

    public Optional<HoldingRecord> getHoldingById(Long id) {
        return holdingRecordRepository.findById(id);
    }
}
