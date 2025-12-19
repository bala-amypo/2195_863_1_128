package com.example.demo.entity;
import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
@Entity 
@Table(name = "asset_class_allocation_rules")
public class AssetClassAllocationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
}