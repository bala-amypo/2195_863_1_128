package com.example.demo.service;

import com.example.demo.entity.AllocationSnapshotRecord;
import java.util.List;

public interface AllocationSnapshotService {

    AllocationSnapshotRecord computeSnapshot(Long investorId);

    AllocationSnapshotRecord getSnapshotById(Long snapshotId);

    List<AllocationSnapshotRecord> getAllSnapshots();
}
