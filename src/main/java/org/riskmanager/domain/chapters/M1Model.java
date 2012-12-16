package org.riskmanager.domain.chapters;

public class M1Model extends GroupParameterModel {

    private Integer parameterCount = Integer.valueOf(20);



    private Double[] parameterWeights = new Double[]{
        0.0581, 0.0291, 0.0502, 0.0461, 0.0522,
                0.061, 0.0522, 0.0661, 0.0661, 0.1001,
                0.0513, 0.0371, 0.0302, 0.0302, 0.0433,
                0.0391, 0.0383, 0.0449, 0.0582, 0.0462
    };

    private Integer[] parameterRequirements = {1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1};

    private Double[] parameterValues;

    public M1Model() {
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

    public Integer getParameterCount() {
        return parameterCount;
    }


}
