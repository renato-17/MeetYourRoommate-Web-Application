package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Student;
<<<<<<< HEAD
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> MeetYourRoommate/RenatoArredondo
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
<<<<<<< HEAD
    public Optional<Student> findByDni(String dni);
=======
    Optional<Student> findByDni(String dni);
    Page<Student> findByTeamId(Long teamId, Pageable pageable);
>>>>>>> MeetYourRoommate/RenatoArredondo
}
