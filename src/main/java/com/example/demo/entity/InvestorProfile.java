package com.example.demo.entity:

import jakarta.presistence.*;
import java.time.LocalDataTime;

@Entity
@Table(name="investor_profiles" , uniqueConstraint(columnNames ="investorId"), @UniqueConstraint(columnNames = "email"))
