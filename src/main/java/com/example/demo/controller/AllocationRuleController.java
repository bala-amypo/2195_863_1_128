package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
public class AllocationRuleController {

    private final AllocationRuleService service;

    public AllocationRuleController(AllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createRule(@RequestBody AssetClassAllocationRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> getRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRuleById(id));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<AssetClassAllocationRule>> getRulesByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.getRulesByInvestor(investorId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AssetClassAllocationRule> updateRule(@PathVariable Long id, @RequestParam Double targetPercentage) {
        return ResponseEntity.ok(service.updateRule(id, targetPercentage));
    }
}
