package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SavePropertyResourceResource {
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String type;

    @NotNull
    private Date date;

    public String getType() {
        return type;
    }

    public SavePropertyResourceResource setType(String type) {
        this.type = type;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SavePropertyResourceResource setDate(Date date) {
        this.date = date;
        return this;
    }
}
