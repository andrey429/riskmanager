package org.selfassess.domain;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


/*
Class for associating self assessment  with something...To store, to save, to load
* */

@Entity
@Table()
public class SelfAssessmentModel {



    @Id
    @Column()
    @GeneratedValue
    private Integer id;


    @Column()
    private String selfAssessmentName;

    @Column()
    private String description;

    @Column
    private String auditors;

    @Column
    private String creator;



    @OneToOne()
    EV1Model ev1Model;


    public SelfAssessmentModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EV1Model getEv1Model() {
        return ev1Model;
    }

    public void setEv1Model(EV1Model ev1Model) {
        this.ev1Model = ev1Model;
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


    public String getAuditors() {
        return auditors;
    }

    public void setAuditors(String auditors) {
        this.auditors = auditors;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    @Override
    public String toString() {
        return selfAssessmentName;
    }
}
