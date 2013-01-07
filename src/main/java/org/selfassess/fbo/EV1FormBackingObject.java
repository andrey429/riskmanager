package org.selfassess.fbo;

import org.selfassess.utils.EV1ValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.01.13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class EV1FormBackingObject {


    private List<ArrayList<Double>> parameterValues;
    private List<ArrayList<Double>> parameterWeightedRecalculatedValues;

    private List<Double> mGroupValues;
    private Double ev1Value;


    public EV1FormBackingObject() {

        EV1ValueFactory ev1ValueFactory = new EV1ValueFactory();
        parameterValues = new ArrayList<ArrayList<Double>>();
        parameterWeightedRecalculatedValues = new ArrayList<ArrayList<Double>>();
        mGroupValues = new ArrayList<Double>();

        for (int i = 0; i < 10; i++) {
            ArrayList<Double> mIdxValues = new ArrayList<Double>();
            ArrayList<Double> mTempValues2 = new ArrayList<Double>();
            for (int j = 0; j < ev1ValueFactory.getCounts()[i]; j++) {
                mIdxValues.add(j, new Double(0));
                mTempValues2.add(j, new Double(0));
            }

            parameterValues.add(i, mIdxValues);
            parameterWeightedRecalculatedValues.add(i, mTempValues2);

            mGroupValues.add(i, new Double(0));

        }
        ev1Value = new Double(0);
    }

    public EV1FormBackingObject(boolean createEmpty) {
        /*EV1ValueFactory ev1ValueFactory = new EV1ValueFactory();
        parameterWeightedRecalculatedValues = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Double> mIdxValues = new ArrayList<Double>();

            parameterWeightedRecalculatedValues.add(i, mIdxValues);

        }
*/    }

    public List<ArrayList<Double>> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(List<ArrayList<Double>> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public List<Double> getmGroupValues() {
        return mGroupValues;
    }

    public void setmGroupValues(List<Double> mGroupValues) {
        this.mGroupValues = mGroupValues;
    }


    public Double getEv1Value() {
        return ev1Value;
    }

    public void setEv1Value(Double ev1Value) {
        this.ev1Value = ev1Value;
    }

    public List<ArrayList<Double>> getParameterWeightedRecalculatedValues() {
        return parameterWeightedRecalculatedValues;
    }

    public void setParameterWeightedRecalculatedValues(List<ArrayList<Double>> parameterWeightedRecalculatedValues) {
        this.parameterWeightedRecalculatedValues = parameterWeightedRecalculatedValues;
    }
}
