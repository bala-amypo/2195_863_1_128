package com.example.demo.service.impl;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        // minimal implementation
        return new AllocationSnapshotRecord();
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long snapshotId) {
        return new AllocationSnapshotRecord();
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return Collections.emptyList();
    }
}
