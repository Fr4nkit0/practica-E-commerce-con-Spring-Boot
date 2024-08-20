package com.example.persistence.entity;

import com.example.persistence.util.Status;
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
    @Column(name = "category_id" , nullable = false)
    private Integer categoryId;
    @Enumerated(EnumType.STRING)
    private Status status ;

    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category ;



}
