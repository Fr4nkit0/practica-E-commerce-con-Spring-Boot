package com.example.persistence.repository.security;

import com.example.persistence.entity.security.JwtToken;
import com.example.service.auth.JwtService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken,Integer> {
    @Query("SELECT j FROM JwtToken  j WHERE j.token=?1")
    Optional<JwtToken> findByToken(String jwt);


}
