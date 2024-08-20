package com.example.persistence.entity.security;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id ;
    @Column(nullable = false , length = 45)
    private String name;
    @Column(nullable = false , length = 12 , unique = true)
    private String username;
    @Column(nullable = false , length = 255)
    private String password;
    @Column(nullable = false, length =20 )
    private String phoneNumber;
    @Column(nullable = false , length = 250)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role ==null) return null;
        if (role.getPermissions()==null) return null;

        List<SimpleGrantedAuthority> authorities= new java.util.ArrayList<>(role.getPermissions().stream()
                .map(each -> new SimpleGrantedAuthority(each.getOperation().getName()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.getName()));
        return  Collections.unmodifiableList(authorities);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
