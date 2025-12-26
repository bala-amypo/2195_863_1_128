package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import java.util.*;

public class RebalancingAlertServiceImpl {

    private final RebalancingAlertRecordRepository repo;

    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository repo) {
        this.repo = repo;
    }

    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage())
            throw new IllegalArgumentException("currentPercentage > targetPercentage required");
        return repo.save(alert);
    }

    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found " + id));
        alert.setResolved(true);
        return repo.save(alert);
    }

    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}
