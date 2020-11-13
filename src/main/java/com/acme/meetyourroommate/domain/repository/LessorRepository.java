package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessorRepository extends JpaRepository<Lessor, Long>{
    Optional<Lessor> findByDni(String dni);
}
