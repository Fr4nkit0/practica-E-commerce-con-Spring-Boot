package com.example.persistence.entity;

import jakarta.persistence.*;
import com.example.persistence.util.Status;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false , length = 45)
    private String name;
    @Embedded
    @Enumerated(EnumType.STRING)
    private Status Status ;
}
