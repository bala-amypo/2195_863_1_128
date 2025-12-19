package com.example.demo.entity:

import jakarta.presistence.*;
import java.time.LocalDataTime;

@Entity
@Table(name="investor_profiles" , uniqueConstraints = { @UniqueConstraint(columnNames ="investorId"), @UniqueConstraint(columnNames = "email")})

public class InventorProfile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String investorId;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Boolean active = true;
    @Column (nullable = false , updatable = false)
    private LocalDataTime createdAt;
    public InvestorProfile(){
        this.active = true;
        this.createdAt = LocalDataTime.now();
    }
    public InvestorProfile(String investorId , String fullName ,String email)
    {
        
    }
}
