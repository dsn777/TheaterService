package com.dsn.Theater.API.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Ticket> tickets;
}
