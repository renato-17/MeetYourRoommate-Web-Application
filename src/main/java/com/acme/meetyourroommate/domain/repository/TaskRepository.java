package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByTeamId(Long teamId, Pageable pageable);
    Optional<Task> findByIdAndTeamId(Long id, Long teamId);
}
