package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Comment;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.CommentRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.CommentService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public AdRepository adRepository;

    @Autowired
    public CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllCommentsByAdId(Long adId, Pageable pageable) {
        return commentRepository.findByAdId(adId, pageable);
    }

    @Override
    public Comment getCommentByIdAndAdId(Long adId, Long commentId) {
        return commentRepository.findByIdAndAdId(commentId, adId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + commentId +
                                " and AdId " + adId));
    }

    @Override
    public Comment createComment(Long adId, Comment comment) {
        return adRepository.findById(adId).map(ad -> {
            comment.setAd(ad);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Ad", "Id", adId));
    }

    @Override
    public Comment updateComment(Long adId, Long commentId, Comment commentDetails) {
        if (!adRepository.existsById(adId))
            throw new ResourceNotFoundException("Ad", "Id", adId);
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentDetails.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long adId, Long commentId) {
        if (!adRepository.existsById(adId))
            throw new ResourceNotFoundException("Ad", "Id", adId);
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }
}
