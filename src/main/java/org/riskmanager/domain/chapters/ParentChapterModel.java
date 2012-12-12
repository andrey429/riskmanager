package org.riskmanager.domain.chapters;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 12.12.12
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class ParentChapterModel {



    private Double[] mParameterValue;
    private Double[] mParamterSignificance;
    private Double[] mParameterResult;

    public ParentChapterModel(int n){
        mParameterResult = new Double[n];
        mParameterValue = new Double[n];
        mParamterSignificance = new Double[n];
    }

    public Double[] getmParameterValue() {
        return mParameterValue;
    }

    public void setmParameterValue(Double[] mParameterValue) {
        this.mParameterValue = mParameterValue;
    }

    public Double[] getmParamterSignificance() {
        return mParamterSignificance;
    }

    public void setmParamterSignificance(Double[] mParamterSignificance) {
        this.mParamterSignificance = mParamterSignificance;
    }

    public Double[] getmParameterResult() {
        return mParameterResult;
    }

    public void setmParameterResult(Double[] mParameterResult) {
        this.mParameterResult = mParameterResult;
    }
}
