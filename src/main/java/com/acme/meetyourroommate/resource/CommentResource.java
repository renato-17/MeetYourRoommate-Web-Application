package com.acme.meetyourroommate.resource;

public class CommentResource {
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public CommentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentResource setText(String text) {
        this.text = text;
        return this;
    }
}
