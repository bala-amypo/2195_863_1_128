package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class HoldingRecordServiceImpl {

    private final HoldingRecordRepository repo;

    public HoldingRecordServiceImpl(HoldingRecordRepository repo) {
        this.repo = repo;
    }

    public HoldingRecord recordHolding(HoldingRecord record) {
        if (record.getCurrentValue() <= 0)
            throw new IllegalArgumentException("Value must be > 0");
        return repo.save(record);
    }

    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    public Optional<HoldingRecord> getHoldingById(Long id) {
        return repo.findById(id);
    }
}
