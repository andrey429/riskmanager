package org.riskmanager.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * For a complete reference see
 * <a href="http://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">
 * Hibernate Annotations Communit Documentations</a>
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {


    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SECOND_NAME")
    private String secondName;


    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "JOB_POSITION")
    private String jobPosition;

    @ManyToOne(targetEntity = Organisation.class)
    private Organisation organisation;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + ": " + jobPosition +" ("+department+")";
    }
}
