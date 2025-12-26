package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshot;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    @Override
    public List<AllocationSnapshot> getSnapshotsByInvestor(Long investorId) {
        // Minimal implementation so application can start
        return Collections.emptyList();
    }
}
