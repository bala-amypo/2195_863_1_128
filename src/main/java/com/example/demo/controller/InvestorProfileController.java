package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    private final InvestorProfileService investorProfileService;

    public InvestorProfileController(InvestorProfileService investorProfileService) {
        this.investorProfileService = investorProfileService;
    }

    @PostMapping
    public ResponseEntity<InvestorProfile> createInvestor(@RequestBody InvestorProfile investor) {
        return ResponseEntity.ok(investorProfileService.createInvestor(investor));
    }

    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllInvestors() {
        return ResponseEntity.ok(investorProfileService.getAllInvestors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getInvestorById(@PathVariable Long id) {
        return ResponseEntity.ok(investorProfileService.getInvestorById(id));
    }

    @GetMapping("/by-investor-id/{investorId}")
    public ResponseEntity<InvestorProfile> getByInvestorId(@PathVariable String investorId) {
        return investorProfileService.findByInvestorId(investorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InvestorProfile> updateStatus(@PathVariable Long id, @RequestParam Boolean status) {
        return ResponseEntity.ok(investorProfileService.updateInvestorStatus(id, status));
    }
}
