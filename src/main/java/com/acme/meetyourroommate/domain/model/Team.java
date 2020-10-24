package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
<<<<<<< HEAD
    private Long name;
=======
    private String name;
>>>>>>> MeetYourRoommate/RenatoArredondo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Long getName() {
        return name;
    }

    public void setName(Long name) {
=======
    public String getName() {
        return name;
    }

    public void setName(String name) {
>>>>>>> MeetYourRoommate/RenatoArredondo
        this.name = name;
    }
}
