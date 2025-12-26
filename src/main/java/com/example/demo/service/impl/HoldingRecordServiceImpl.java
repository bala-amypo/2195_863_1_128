package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        // minimal implementation
        return Collections.emptyList();
    }

    @Override
    public HoldingRecord getHoldingById(Long holdingId) {
        return new HoldingRecord();
    }
}
