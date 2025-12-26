package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import java.util.*;

public class InvestorProfileServiceImpl {

    private final InvestorProfileRepository repo;

    public InvestorProfileServiceImpl(InvestorProfileRepository repo) {
        this.repo = repo;
    }

    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repo.save(investor);
    }

    public InvestorProfile getInvestorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found " + id));
    }

    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }

    public InvestorProfile updateInvestorStatus(Long id, Boolean active) {
        InvestorProfile inv = getInvestorById(id);
        inv.setActive(active);
        return repo.save(inv);
    }

    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repo.findByInvestorId(investorId);
    }
}
