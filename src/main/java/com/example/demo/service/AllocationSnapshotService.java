package com.example.demo.service;

import java.util.List;

public interface AllocationSnapshotService {

    Object computeSnapshot(Long investorId);

    Object getSnapshotById(Long snapshotId);

    List<Object> getAllSnapshots();
}
