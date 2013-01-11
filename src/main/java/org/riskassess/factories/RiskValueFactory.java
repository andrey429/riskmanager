package org.riskassess.factories;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class RiskValueFactory {

    private ReloadableResourceBundleMessageSource messageSource;

    private final Integer ACCEPTABLE_RISK_VALUE = new Integer(1);
    private final Integer NOT_ACCEPTABLE_RISK_VALUE = new Integer(0);

    //first idx is SVR level, second is STP level, e.g.
    // riskValueMatrix[1][3] = riskValue for SVR level, 2 STP level 4
    private final Integer[][] riskValueMatrix =
            {
                    {ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE},
                    {ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE},
                    {ACCEPTABLE_RISK_VALUE, ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE},
                    {ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE},
                    {NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE, NOT_ACCEPTABLE_RISK_VALUE}
            };

    public RiskValueFactory(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getRiskValueString(int svrLevel, int stpLevel){
        return getRiskValue(svrLevel, stpLevel) == ACCEPTABLE_RISK_VALUE ?
                messageSource.getMessage("values.acceptable", null, Locale.getDefault()) :
                messageSource.getMessage("values.notAcceptable", null, Locale.getDefault());
    }

    public Integer getRiskValue(int svrLevel, int stpLevel){
        return riskValueMatrix[svrLevel][stpLevel];
    }


    public Integer[][] getRiskValueMatrix() {
        return riskValueMatrix;
    }
}
