package com.acme.meetyourroommate.domain.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Lessor extends Person {

    @NotNull
    private Boolean bPremium;

    public Boolean getbPremium() {
        return bPremium;
    }

    public void setbPremium(Boolean bPremium) {
        this.bPremium = bPremium;
    }
}