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
}
