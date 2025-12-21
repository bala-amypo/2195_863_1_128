package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService service;

    public RebalancingAlertController(RebalancingAlertService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RebalancingAlertRecord> createAlert(@RequestBody RebalancingAlertRecord alert) {
        return ResponseEntity.ok(service.createAlert(alert));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebalancingAlertRecord> getAlertById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAlertById(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<RebalancingAlertRecord>> getAlertsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.getAlertsByInvestor(investorId));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<RebalancingAlertRecord> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveAlert(id));
    }

    @GetMapping
    public ResponseEntity<List<RebalancingAlertRecord>> getAllAlerts() {
        return ResponseEntity.ok(service.getAllAlerts());
    }
}
