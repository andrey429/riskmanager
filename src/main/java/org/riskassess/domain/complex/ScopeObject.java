package org.riskassess.domain.complex;
import org.riskassess.domain.basic.AssetType;
import org.riskassess.domain.basic.MediaType;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class ScopeObject {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @OneToOne
    private AssetType assetType;

    @OneToOne
    private MediaType mediaType;

    @OneToMany
    private List<RiskDetail> riskDetails;

    public ScopeObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public List<RiskDetail> getRiskDetails() {
        return riskDetails;
    }

    public void setRiskDetails(List<RiskDetail> riskDetails) {
        this.riskDetails = riskDetails;
    }


}
