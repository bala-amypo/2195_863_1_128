package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebalancingAlertServiceImpl implements com.example.demo.service.RebalancingAlertService {

    private final RebalancingAlertRecordRepository alertRecordRepository;

    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository alertRecordRepository) {
        this.alertRecordRepository = alertRecordRepository;
    }

    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        // Enforce constraint from Test 33
        if (alert.getCurrentPercentage() < alert.getTargetPercentage()) {
            throw new IllegalArgumentException("currentPercentage > targetPercentage required for alert");
        }
        return alertRecordRepository.save(alert);
    }

    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = alertRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found with id: " + id));
        alert.setResolved(true);
        return alertRecordRepository.save(alert);
    }

    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return alertRecordRepository.findByInvestorId(investorId);
    }
}
