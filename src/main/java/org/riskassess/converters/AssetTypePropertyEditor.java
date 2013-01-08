package org.riskassess.converters;

import org.riskassess.domain.basic.AssetType;
import org.riskassess.service.HibernateService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public class AssetTypePropertyEditor extends PropertyEditorSupport {


    private HibernateService hibernateService;

    public AssetTypePropertyEditor(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public void setAsText(String text) {
        if (text == null || text.length() == 0) {
            setValue(null);
        } else {
            setValue(this.hibernateService.getAssetType(Integer.parseInt(text.trim())));
        }
    }

    @Override
    public String getAsText() {
        AssetType assetType = (AssetType) this.getValue();
        if (assetType == null) {
            return "";
        } else {
            return Integer.toString(assetType.getId());
        }
    }



}
