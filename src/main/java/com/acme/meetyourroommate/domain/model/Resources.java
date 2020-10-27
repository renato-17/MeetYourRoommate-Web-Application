package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name= "property_resources")
public class Resources extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropertyResource;

    @NotNull
    @Size(max = 50)
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false,updatable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id",nullable = false)
    private Details details;

    public Long getIdPropertyResource() {
        return idPropertyResource;
    }

    public void setIdPropertyResource(Long idPropertyResource) {
        this.idPropertyResource = idPropertyResource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
