package com.acme.meetyourroommate.resource;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePropertyResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    public String getAddress() {
        return address;
    }

    public SavePropertyResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SavePropertyResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
