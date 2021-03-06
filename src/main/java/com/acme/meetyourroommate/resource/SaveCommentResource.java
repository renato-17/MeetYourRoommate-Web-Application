package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCommentResource {
    @NotNull
    @NotBlank
    @Size(max = 250)
    private String text;

    public String getText() {
        return text;
    }

    public SaveCommentResource setText(String text) {
        this.text = text;
        return this;
    }
}
