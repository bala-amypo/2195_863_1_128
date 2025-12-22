package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService service;

    public AllocationSnapshotController(AllocationSnapshotService service) {
        this.service = service;
    }

    @PostMapping("/compute")
    public ResponseEntity<AllocationSnapshotRecord> computeSnapshot(@RequestParam Long investorId) {
        return ResponseEntity.ok(service.computeSnapshot(investorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getSnapshotById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSnapshotById(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AllocationSnapshotRecord>> getSnapshotsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.getSnapshotsByInvestor(investorId));
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotRecord>> getAllSnapshots() {
        return ResponseEntity.ok(service.getAllSnapshots());
    }
}
