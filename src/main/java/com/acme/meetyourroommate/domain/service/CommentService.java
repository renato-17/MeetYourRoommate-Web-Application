package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllCommentsByAdId(Long adId, Pageable pageable);
    Comment getCommentByIdAndAdId(Long adId, Long commentId);
    Comment createComment(Long adId, Comment comment);
    Comment updateComment(Long adId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long adId, Long commentId);
}
