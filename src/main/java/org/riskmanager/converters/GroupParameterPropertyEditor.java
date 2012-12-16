package org.riskmanager.converters;



import org.riskmanager.domain.chapters.GroupParameterModel;


import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 16.12.12
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class GroupParameterPropertyEditor extends PropertyEditorSupport {



    @Override
    public void setAsText(String text) {
        if(text == null || text.length() == 0) {
            setValue(null);
        } else {
            setValue(Double.parseDouble(text));
        }
    }

    @Override
    public String getAsText() {
        Double groupParamVal = (Double)this.getValue();
        if(groupParamVal == null) {
            return "";
        } else {
            return Double.toString(groupParamVal);
        }
    }



}
