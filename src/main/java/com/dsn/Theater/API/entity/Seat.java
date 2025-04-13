package com.dsn.Theater.API.entity;

import com.dsn.Theater.API.entity.enums.Level;
import com.dsn.Theater.API.entity.enums.Section;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Section section;
    private Integer sectionNumber;
    private Integer number;
}
