package com.dsn.Theater.API.entity;

import com.dsn.Theater.API.entity.enums.PerformanceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String duration;

    @Enumerated(EnumType.STRING)
    private PerformanceType type;

    @Column(length = 2048)
    private String description;
}
