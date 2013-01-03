package org.selfassess.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table
public class EV1Model implements Serializable {


    private static final long serialVersionUID = 5651402584668511524L;
    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne()
    private NumbersList mGroupValues;

    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ListOfList mParamValues;


    @Column
    private Double ev1value;


    public EV1Model() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NumbersList getmGroupValues() {
        return mGroupValues;
    }

    public void setmGroupValues(NumbersList mGroupValues) {
        this.mGroupValues = mGroupValues;
    }

    public Double getEv1value() {
        return ev1value;
    }

    public void setEv1value(Double ev1value) {
        this.ev1value = ev1value;
    }


    public ListOfList getmParamValues() {
        return mParamValues;
    }

    public void setmParamValues(ListOfList mParamValues) {
        this.mParamValues = mParamValues;
    }
}