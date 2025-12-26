package com.example.demo.service;

import com.example.demo.entity.AllocationSnapshot;
import java.util.List;

public interface AllocationSnapshotService {

    List<AllocationSnapshot> getSnapshotsByInvestor(Long investorId);
}
