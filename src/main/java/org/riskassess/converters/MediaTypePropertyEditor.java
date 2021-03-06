package org.riskassess.converters;

import org.riskassess.domain.basic.MediaType;
import org.riskassess.service.HibernateService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 08.01.13
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class MediaTypePropertyEditor extends PropertyEditorSupport {

    private HibernateService hibernateService;

    public MediaTypePropertyEditor(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public void setAsText(String text) {
        if (text == null || text.length() == 0) {
            setValue(null);
        } else {
            setValue(this.hibernateService.getMediaType(Integer.parseInt(text.trim())));
        }
    }

    @Override
    public String getAsText() {
        MediaType mediaType = (MediaType) this.getValue();
        if (mediaType == null) {
            return "";
        } else {
            return Integer.toString(mediaType.getId());
        }
    }

}
