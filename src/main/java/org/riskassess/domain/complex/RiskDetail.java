package org.riskassess.domain.complex;
import javax.persistence.*;
/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table
public class RiskDetail {

    @Id
    @GeneratedValue
    @Column
    private Integer id;


    @Column
    private Integer threatClass;
    @Column
    private Integer threatSource;

    @Column
    private Integer targetProperty;//conf, integr, avail

    @Column
    private String threatImplementation;

    @Column
    private String aprioryProtectionMeasures;

    @Column
    private String aposterioryProtectionMeasures;

    @Column
    private String otherDataForSVR;

    @Column
    private String otherDataForSTP;

    @Column
    private Integer svrValue;

    @Column
    private Integer stpValue;

    public RiskDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThreatClass() {
        return threatClass;
    }

    public void setThreatClass(Integer threatClass) {
        this.threatClass = threatClass;
    }

    public Integer getThreatSource() {
        return threatSource;
    }

    public void setThreatSource(Integer threatSource) {
        this.threatSource = threatSource;
    }

    public Integer getTargetProperty() {
        return targetProperty;
    }

    public void setTargetProperty(Integer targetProperty) {
        this.targetProperty = targetProperty;
    }

    public String getThreatImplementation() {
        return threatImplementation;
    }

    public void setThreatImplementation(String threatImplementation) {
        this.threatImplementation = threatImplementation;
    }

    public String getAprioryProtectionMeasures() {
        return aprioryProtectionMeasures;
    }

    public void setAprioryProtectionMeasures(String aprioryProtectionMeasures) {
        this.aprioryProtectionMeasures = aprioryProtectionMeasures;
    }

    public String getAposterioryProtectionMeasures() {
        return aposterioryProtectionMeasures;
    }

    public void setAposterioryProtectionMeasures(String aposterioryProtectionMeasures) {
        this.aposterioryProtectionMeasures = aposterioryProtectionMeasures;
    }

    public String getOtherDataForSVR() {
        return otherDataForSVR;
    }

    public void setOtherDataForSVR(String otherDataForSVR) {
        this.otherDataForSVR = otherDataForSVR;
    }

    public String getOtherDataForSTP() {
        return otherDataForSTP;
    }

    public void setOtherDataForSTP(String getOtherDataForSTP) {
        this.otherDataForSTP = getOtherDataForSTP;
    }

    public Integer getSvrValue() {
        return svrValue;
    }

    public void setSvrValue(Integer svrValue) {
        this.svrValue = svrValue;
    }

    public Integer getStpValue() {
        return stpValue;
    }

    public void setStpValue(Integer stpValue) {
        this.stpValue = stpValue;
    }

}
