package com.acme.meetyourroommate.domain.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Lessor extends Person {

    @NotNull
    private Boolean premium;

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
}
