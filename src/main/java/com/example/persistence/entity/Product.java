package com.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (nullable = false , length = 45)
    private String name;
    @Column (nullable = false , length = 255)
    private String description;
    @Column (nullable = false)
    private BigDecimal price;
    @ManyToOne(optional = false)
    private Category category;
}
