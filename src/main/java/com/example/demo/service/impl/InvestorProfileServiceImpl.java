package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestorProfileServiceImpl implements com.example.demo.service.InvestorProfileService {

    private final InvestorProfileRepository investorProfileRepository;

    public InvestorProfileServiceImpl(InvestorProfileRepository investorProfileRepository) {
        this.investorProfileRepository = investorProfileRepository;
    }

    public InvestorProfile createInvestor(InvestorProfile investor) {
        return investorProfileRepository.save(investor);
    }

    public InvestorProfile getInvestorById(Long id) {
        return investorProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found with id: " + id));
    }

    public List<InvestorProfile> getAllInvestors() {
        return investorProfileRepository.findAll();
    }

    public InvestorProfile updateInvestorStatus(Long id, Boolean status) {
        InvestorProfile investor = getInvestorById(id);
        investor.setActive(status);
        return investorProfileRepository.save(investor);
    }

    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId);
    }
}
