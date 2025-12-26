package com.example.demo.repository;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AllocationSnapshotRecordRepository extends JpaRepository<AllocationSnapshotRecord, Long> {}
