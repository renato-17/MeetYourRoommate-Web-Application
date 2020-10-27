package com.meetyourroomate.domain.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "campus")
public class Campus extends AuditModel {

    @Id
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 100)
    private String adresss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id",nullable = false)
    private StudyCenter studyCenter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresss() {
        return adresss;
    }

    public void setAdresss(String adresss) {
        this.adresss = adresss;
    }

    public StudyCenter getStudyCenter() {
        return studyCenter;
    }

    public void setStudyCenter(StudyCenter studyCenter) {
        this.studyCenter = studyCenter;
    }
}
