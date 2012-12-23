package org.riskmanager.domain.chapters;

import org.hibernate.annotations.CollectionOfElements;
import org.riskmanager.service.EV1ValueFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "EV1_EVALUATIONS")
public class EV1Model {


    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;


    @CollectionOfElements
    private List<Double> mGroupValues;

    @Column(name = "EV1_VALUE")
    private Double ev1value;


    @CollectionOfElements
    private List<ArrayList<Double>> parameterValues;

    public EV1Model() {

        EV1ValueFactory ev1ValueFactory = new EV1ValueFactory();
        parameterValues = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Double> mIdxValues = new ArrayList<Double>();
            for (int j = 0; j < ev1ValueFactory.getCounts()[i]; j++) {
                mIdxValues.add(j, Double.valueOf(0));

            }
            parameterValues.add(i, mIdxValues);

        }
    }


    public List<ArrayList<Double>> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(List<ArrayList<Double>> parameterValues) {
        this.parameterValues = parameterValues;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Double> getmGroupValues() {
        return mGroupValues;
    }

    public void setmGroupValues(List<Double> mGroupValues) {
        this.mGroupValues = mGroupValues;
    }

    public Double getEv1value() {
        return ev1value;
    }

    public void setEv1value(Double ev1value) {
        this.ev1value = ev1value;
    }



}