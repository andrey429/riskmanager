package org.riskmanager.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.10.12
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ASSET")
public class Asset implements Serializable {


    private static final long serialVersionUID = 7306985946511236081L;
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

    @ManyToOne(targetEntity = Person.class)
    private Person personOwner;


    /*@Column(name = "DAMAGE__IF_CONF_LOST")
    private Double damageIfConfidentialityLost;

    @Column(name = "DAMAGE_IF_INT_LOST")
    private Double damageIfIntegrityLost;

    @Column(name = "DAMAGE_IF_AVL_LOST")
    private Double damageIfAvailabilityLost;
      */


    @Column(name = "BUSINESS_PROCESS_TYPE")
    private Integer businessProcessType;

    @Column(name = "ASSET_LOCATION")
    private String assetLocation;


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

    public Integer getBusinessProcessType() {
        return businessProcessType;
    }

    public void setBusinessProcessType(Integer businessProcessType) {
        this.businessProcessType = businessProcessType;
    }

    public String getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
    }

    public Person getPersonOwner() {
        return personOwner;
    }

    public void setPersonOwner(Person personOwner) {
        this.personOwner = personOwner;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
