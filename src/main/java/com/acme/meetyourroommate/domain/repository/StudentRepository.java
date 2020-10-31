package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByDni(String dni);
    Page<Student> findByTeamId(Long teamId, Pageable pageable);
}
