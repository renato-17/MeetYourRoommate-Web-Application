package com.acme.meetyourroommate.domain.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ads")
public class Ad extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String title;

    @NotNull
    private int viewsNumber;

    @NotNull
    private int likesNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="property_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Property property;

    public Long getId() {
        return id;
    }

    public Ad setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Ad setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public Ad setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
        return this;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public Ad setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
        return this;
    }

    public Property getProperty() {
        return property;
    }

    public Ad setProperty(Property property) {
        this.property = property;
        return this;
    }
}
