package org.riskassess.factories;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class ThreatSourceFactory {

    private ReloadableResourceBundleMessageSource messageSource;
    private Integer[] countOfParametersInGroups = {7, 5, 6, 6, 9, 6, 2};


    public ThreatSourceFactory(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getThreatDescription(Integer classNum, Integer threatNum) {
        return messageSource.getMessage(("class" + classNum + ".threat" + threatNum),
                null, Locale.getDefault()
        );
    }


    public Integer[] getCountOfParametersInGroups() {
        return countOfParametersInGroups;
    }

    public String getTargetPropertyString(Integer propertyType){
        switch (propertyType.intValue()){
            case 0:
                return messageSource.getMessage("values.property.confidentiality", null, Locale.getDefault());
            case 1:
                return messageSource.getMessage("values.property.integrity", null, Locale.getDefault());
            case 2:
                return messageSource.getMessage("values.property.availability", null, Locale.getDefault());
            default:
                return messageSource.getMessage("values.property.other", null, Locale.getDefault());

        }
    }

}
