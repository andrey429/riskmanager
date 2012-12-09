package org.riskmanager.domain;


/*
Class for associating risk evaluation with something...To store, to save, to load
* */

import javax.persistence.*;

@Entity
@Table(name = "RISK_ASSESSMENT_MODEL")
 public class RiskAssessmentModel{


    //todo serialversionuid
    //todo connect with domain model


    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;


    @Column(name = "RISK_ASSESSMENT_NAME")
    private String riskAssessmentName;

    @Column(name = "DESCRIPTION")
    private String description;

    public RiskAssessmentModel() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRiskAssessmentName() {
        return riskAssessmentName;
    }

    public void setRiskAssessmentName(String riskAssessmentName) {
        this.riskAssessmentName = riskAssessmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
