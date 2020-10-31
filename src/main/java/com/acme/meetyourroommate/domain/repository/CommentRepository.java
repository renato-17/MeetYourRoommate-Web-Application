package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByAdId(Long adId, Pageable pageable);

    Optional<Comment> findByIdAndAdId(Long id, Long adId);
}
