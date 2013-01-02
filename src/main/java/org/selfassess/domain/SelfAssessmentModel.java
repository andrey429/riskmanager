package org.selfassess.domain;

import javax.persistence.*;


/*
Class for associating self assessment  with something...To store, to save, to load
* */

@Entity
@Table(name = "SELF_ASSESSMENT_MODEL")
public class SelfAssessmentModel {

    //todo serialversionuid
    //todo connect with domain model


    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;


    @Column(name = "SELF_ASSESSMENT_NAME")
    private String selfAssessmentName;

    @Column(name = "DESCRIPTION")
    private String description;


    public SelfAssessmentModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelfAssessmentName() {
        return selfAssessmentName;
    }

    public void setSelfAssessmentName(String selfAssessmentName) {
        this.selfAssessmentName = selfAssessmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
