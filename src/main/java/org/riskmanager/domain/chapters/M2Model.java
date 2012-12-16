package org.riskmanager.domain.chapters;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 15.12.12
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class M2Model {

    private int parameterCount = 17;

    private Double[] parameterWeights = {
            0.0504, 0.0616, 0.0591, 0.0563, 0.0646,
            0.0604, 0.045, 0.0604, 0.0596, 0.0474,
            0.07, 0.0626, 0.0596, 0.0533, 0.0646,
            0.0675, 0.0576
    };

    private Integer[] parameterRequirements = {0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    private Double[] parameterValues;

    public M2Model() {
        parameterValues = new Double[parameterCount];
        for(int i = 0; i < parameterCount; i++){
            parameterValues[i] = Double.valueOf(0);
        }
    }

    public Double[] getParameterWeights() {
        return parameterWeights;
    }

    public void setParameterWeights(Double[] parameterWeights) {
        this.parameterWeights = parameterWeights;
    }

    public Integer[] getParameterRequirements() {
        return parameterRequirements;
    }

    public void setParameterRequirements(Integer[] parameterRequirements) {
        this.parameterRequirements = parameterRequirements;
    }

    public Double[] getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Double[] parameterValues) {
        this.parameterValues = parameterValues;
    }
}
