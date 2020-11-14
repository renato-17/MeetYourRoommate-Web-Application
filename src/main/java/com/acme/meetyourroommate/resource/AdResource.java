package com.acme.meetyourroommate.resource;

public class AdResource {
    private Long id;
    private String title;
    private int viewsNumber;
    private int likesNumber;

    public Long getId() {
        return id;
    }

    public AdResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AdResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public AdResource setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
        return this;

    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public AdResource setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
        return this;
    }
}
