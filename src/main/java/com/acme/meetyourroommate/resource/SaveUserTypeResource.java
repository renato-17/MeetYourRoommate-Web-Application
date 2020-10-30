package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserTypeResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
