package com.example.persistence.repository.security;

import com.example.persistence.entity.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Integer> {

    @Query("SELECT o FROM Operation o WHERE o.permitAll=true")
    List<Operation> findByPublicAccess ();

    Optional<Operation> findByName(String operation);
}
