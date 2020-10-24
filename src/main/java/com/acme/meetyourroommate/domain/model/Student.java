package com.acme.meetyourroommate.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Student extends Person{

    @NotNull
    private String description;

    @NotNull
    private String hobbies;

    @NotNull
    private Boolean bSmoker;

    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name = "team_id" ,nullable = false)
    @JsonIgnore
    private Team team;

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
