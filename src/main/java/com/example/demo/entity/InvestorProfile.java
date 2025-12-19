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
    @Column(nullable)
}
