package com.acme.meetyourroommate.resource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveTaskResource {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
