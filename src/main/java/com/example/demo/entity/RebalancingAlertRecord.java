package com.example.demo.entity;

import com.example.demo.entity.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public Double getCurrentPercentage() {
    return currentPercentage;
}

public Double getTargetPercentage() {
    return targetPercentage;
}

public AlertSeverity getSeverity() {
    return severity;
}

public Boolean getResolved() {
    return resolved;
}
