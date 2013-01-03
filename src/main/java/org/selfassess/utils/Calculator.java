package org.selfassess.utils;

import org.selfassess.fbo.EV1FormBackingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 03.01.13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    private EV1ValueFactory valueFactory;

    public Calculator() {
        this.valueFactory = new EV1ValueFactory();
    }

    protected void recalculateEV1GroupParameters(EV1FormBackingObject ev1FBO) {

        List<Double> groupValues = new ArrayList<Double>();
        for (int i = 0; i <= 9; i++) {
            groupValues.add(i, calculateGroup(i, ev1FBO));
        }
        ev1FBO.setmGroupValues(groupValues);
        ev1FBO.setEv1Value(calculateEV1(ev1FBO));
    }


    /*
    *
    groupIdx - starts from 1, not 0
    *
    * */
    private Double calculateGroup(int groupIdx, EV1FormBackingObject ev1FBO) {
        if (groupIdx == 8) {
            return calculateM9Group(ev1FBO);
        }

        double resultVal = 0.0;
        Double[] parameter_weights = valueFactory.getParameterWeights()[groupIdx];
        int count = valueFactory.getCounts()[groupIdx];


        double sumOfNotEvaluatedParamWeights = 0.0;


        for (int j = 0; j < count; j++) {

            double element = ev1FBO.getParameterValues().get(groupIdx).get(j);
            if (element == -1) {
                sumOfNotEvaluatedParamWeights += parameter_weights[j].doubleValue();
            }
        }

        double denominator = (1 - sumOfNotEvaluatedParamWeights);

        if (denominator != 0) {//todo
            for (int j = 0; j < count; j++) {
                double element = ev1FBO.getParameterValues().get(groupIdx).get(j).doubleValue();
                if (element != -1) { //use only evaluated parameters
                    resultVal += element * (parameter_weights[j].doubleValue() / denominator);
                }
            }
            return roundToFourDigits(resultVal);
        } else
            return -1.0;

    }

    private Double calculateM9Group(EV1FormBackingObject ev1FBO) {

        int count = valueFactory.getCounts()[8];
        double resultVal = 1.0;
            /*searchin minimum in [0.0, 1.0]*/
        int countChanges = 0;


        for (int j = 0; j < count; j++) {
            double element = ev1FBO.getParameterValues().get(8).get(j).doubleValue();
            if (element != -1) { //use only evaluated parameters
                resultVal = Math.min(resultVal, element);
                countChanges++;
            }
        }
        if (countChanges > 0) return roundToFourDigits(resultVal);
        else return -1.0;
    }


    private Double calculateEV1(EV1FormBackingObject ev1FormBackingObject) {
        Double evBankPayProcess;//BPTP
        Double evBankInfoProcess;//BITP
        /*Double evPersonalDataSecurityWithoutCryptoprotection;//OZPD1*/
        Double evPersonalDataSecurityCryptoprotection;//OZPD2
        Double evPersonalDataSecurityUsage;//OOPD
        Double ev1Value;

        double sum = 0;

        for (int i = 0; i <= 4; i++) {
            sum += ev1FormBackingObject.getmGroupValues().get(i);
        }

        evBankPayProcess = (sum + ev1FormBackingObject.getmGroupValues().get(6)
                + ev1FormBackingObject.getmGroupValues().get(5)) / 7;
        evBankInfoProcess = (sum + ev1FormBackingObject.getmGroupValues().get(7)
                + ev1FormBackingObject.getmGroupValues().get(5)) / 7;

        /*evPersonalDataSecurityWithoutCryptoprotection = (sum + ev1FormBackingObject.getmGroupValues().get(7) +
                ev1FormBackingObject.getmGroupValues().get(9)) / 7;*/

        evPersonalDataSecurityCryptoprotection = (sum + ev1FormBackingObject.getmGroupValues().get(7) +
                ev1FormBackingObject.getmGroupValues().get(9) +
                ev1FormBackingObject.getmGroupValues().get(5)) / 8;

        evPersonalDataSecurityUsage = ev1FormBackingObject.getmGroupValues().get(8);

        ev1Value = Math.min(Math.min(evBankInfoProcess, evBankPayProcess),
                Math.min(evPersonalDataSecurityCryptoprotection, evPersonalDataSecurityUsage));

        return roundToFourDigits(ev1Value);
    }


    private Double roundToFourDigits(Double value) {
        if (value.toString().length() > 6) {
            Double temp;
            temp = value.toString().charAt(6) > '4' ? value + 0.0001 : value;
            return new Double(temp.toString().substring(0, 6));
        } else {
            return value;
        }
    }

}
