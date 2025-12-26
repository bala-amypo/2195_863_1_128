package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String investorId;

    private String fullName;

    @Column(unique = true) // 1NF Normalization test
    private String email;

    private Boolean active;

    public InvestorProfile(String investorId, String fullName, String email, Boolean active) {
        this.investorId = investorId;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
    }
}
