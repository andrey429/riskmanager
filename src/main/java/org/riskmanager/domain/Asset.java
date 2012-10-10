package org.riskmanager.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.10.12
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ASSET")
public class Asset {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_REQUIRES_CONFIDENTIALITY")
    private Boolean requiresConfidentiality;

    @Column(name = "IS_REQUIRES_INTEGRITY")
    private Boolean requiresIntegrity;

    @Column(name = "IS_REQUIRES_AVAILABILITY")
    private Boolean requiresAvailability;



    @Column(name = "DAMAGE__IF_CONF_LOST")
    private Double damageIfConfidentialityLost;

    @Column(name = "DAMAGE_IF_INT_LOST")
    private Double damageIfIntegrityLost;

    @Column(name = "DAMAGE_IF_AVL_LOST")
    private Double damageIfAvailabilityLost;

    //todo add owner(one asset to many owners), organisation(one asset to many orgs)


    public Asset() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequiresConfidentiality() {
        return requiresConfidentiality;
    }

    public void setRequiresConfidentiality(Boolean requiresConfidentiality) {
        this.requiresConfidentiality = requiresConfidentiality;
    }

    public Boolean getRequiresIntegrity() {
        return requiresIntegrity;
    }

    public void setRequiresIntegrity(Boolean requiresIntegrity) {
        this.requiresIntegrity = requiresIntegrity;
    }

    public Boolean getRequiresAvailability() {
        return requiresAvailability;
    }

    public void setRequiresAvailability(Boolean requiresAvailability) {
        this.requiresAvailability = requiresAvailability;
    }

    public Double getDamageIfConfidentialityLost() {
        return damageIfConfidentialityLost;
    }

    public void setDamageIfConfidentialityLost(Double damageIfConfidentialityLost) {
        this.damageIfConfidentialityLost = damageIfConfidentialityLost;
    }

    public Double getDamageIfIntegrityLost() {
        return damageIfIntegrityLost;
    }

    public void setDamageIfIntegrityLost(Double damageIfIntegrityLost) {
        this.damageIfIntegrityLost = damageIfIntegrityLost;
    }

    public Double getDamageIfAvailabilityLost() {
        return damageIfAvailabilityLost;
    }

    public void setDamageIfAvailabilityLost(Double damageIfAvailabilityLost) {
        this.damageIfAvailabilityLost = damageIfAvailabilityLost;
    }
}
