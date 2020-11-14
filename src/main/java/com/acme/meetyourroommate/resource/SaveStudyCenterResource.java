package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveStudyCenterResource {
    @NotBlank
    @NotNull
    @Size(max = 40)
    private String name;

    public String getName() {
        return name;
    }

    public SaveStudyCenterResource setName(String name) {
        this.name = name;
        return this;
    }
}
