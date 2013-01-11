package org.riskassess.domain.basic;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class AssetType {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String assetTypeName;

    @Column
    private String description;




    public AssetType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    /*public Boolean getRequiresConf() {
        return requiresConf;
    }

    public void setRequiresConf(Boolean requiresConf) {
        this.requiresConf = requiresConf;
    }

    public Boolean getRequiresIntegr() {
        return requiresIntegr;
    }

    public void setRequiresIntegr(Boolean requiresIntegr) {
        this.requiresIntegr = requiresIntegr;
    }

    public Boolean getRequiresAvail() {
        return requiresAvail;
    }

    public void setRequiresAvail(Boolean requiresAvail) {
        this.requiresAvail = requiresAvail;
    }*/


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return assetTypeName;
    }
}
