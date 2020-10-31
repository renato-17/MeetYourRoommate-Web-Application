package com.acme.meetyourroommate.domain.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "properties")
public class Property extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String address;

    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public Property setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Property setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }
}
