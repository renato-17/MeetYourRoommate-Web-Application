package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveAdResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private int viewsNumber;

    @NotNull
    private int likesNumber;

    public String getTitle() {
        return title;
    }

    public SaveAdResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public SaveAdResource setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
        return this;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public SaveAdResource setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
        return this;
    }
}
