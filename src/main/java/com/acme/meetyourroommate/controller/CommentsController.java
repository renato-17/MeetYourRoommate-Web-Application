package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Comment;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.CommentRepository;
import com.acme.meetyourroommate.domain.service.CommentService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.resource.AdResource;
import com.acme.meetyourroommate.resource.CommentResource;
import com.acme.meetyourroommate.resource.SaveAdResource;
import com.acme.meetyourroommate.resource.SaveCommentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;

    @GetMapping("properties/{propertyId}/ads/{adId}/comments")
    public Page<CommentResource> getAllCommentsByAdId (
            @PathVariable Long adId, Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllCommentsByAdId(adId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("properties/{propertyId}/ads/{adId}/comments")
    public CommentResource createComment(
            @PathVariable Long adId, @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(adId, convertToEntity(resource)));
    }

    @PutMapping("properties/{propertyId}/ads/{adId}/comments/{commentId}")
    public CommentResource updateComment (
            @PathVariable(value="adId") Long adId,
            @PathVariable(value="commentId") Long commentId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.updateComment(adId, commentId, convertToEntity(resource)));
    }

    @DeleteMapping("properties/{propertyId}/ads/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable(value="adId") Long adId,
            @PathVariable (value = "commentId") Long commentId) {
        return commentService.deleteComment(adId, commentId);
    }
    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }
}
