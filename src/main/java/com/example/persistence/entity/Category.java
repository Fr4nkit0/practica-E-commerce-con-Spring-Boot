package com.example.persistence.entity;

import jakarta.persistence.*;
import com.example.persistence.util.Status;
import lombok.*;

import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Status status ;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private List<Product> products;

}
