package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotNull;

public class FindReservationDetail {

    @NotNull
    private int type;

    @NotNull
    private Long id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
