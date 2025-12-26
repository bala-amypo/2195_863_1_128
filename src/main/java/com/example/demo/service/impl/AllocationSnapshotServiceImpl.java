package com.example.demo.service.impl;

import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    @Override
    public Object computeSnapshot(Long investorId) {
        return new Object();
    }

    @Override
    public Object getSnapshotById(Long snapshotId) {
        return new Object();
    }

    @Override
    public List<Object> getAllSnapshots() {
        return Collections.emptyList();
    }
}
