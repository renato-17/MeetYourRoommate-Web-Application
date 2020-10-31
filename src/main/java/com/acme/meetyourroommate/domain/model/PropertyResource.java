package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name= "property_resources")
public class PropertyResource extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date",nullable = false,updatable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id",nullable = false)
    private PropertyDetail details;

    public Long getId() {
        return id;
    }

    public void setId(Long idPropertyResource) {
        this.id = idPropertyResource;
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

    public PropertyDetail getDetails() {
        return details;
    }

    public void setDetails(PropertyDetail details) {
        this.details = details;
    }
}
