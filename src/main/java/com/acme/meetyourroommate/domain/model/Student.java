package com.acme.meetyourroommate.domain.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends Person{

    @NotNull
    private String description;

    @NotNull
    private String hobbies;

    @NotNull
    private Boolean bSmoker;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Boolean getbSmoker() {
        return bSmoker;
    }

    public void setbSmoker(Boolean bSmoker) {
        this.bSmoker = bSmoker;
    }
}
