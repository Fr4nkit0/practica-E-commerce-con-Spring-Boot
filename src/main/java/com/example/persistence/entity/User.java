package com.example.persistence.entity;

import com.example.persistence.util.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id ;
    @Column(nullable = false , length = 12 , unique = true)
    private String username;
    @Column(nullable = false , length = 16)
    private String password;
    @Column(nullable = false, length =20 )
    private String phoneNumber;
    @Column(nullable = false , length = 250)
    private String email;

    private Role role;
}
