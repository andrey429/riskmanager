package org.riskmanager.converters;

import org.riskmanager.domain.Organisation;
import org.riskmanager.service.OrganisationService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 13.10.12
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class OrganisationPropertyEditor extends PropertyEditorSupport{



        private OrganisationService organisationService;

        public OrganisationPropertyEditor(OrganisationService organisationService) {
            this.organisationService = organisationService;
        }

    @Override
        public void setAsText(String text) {
            if(text == null || text.length() == 0) {
                setValue(null);
            } else {
                setValue(this.organisationService.get(Integer.parseInt(text.trim())));
            }
        }

        @Override
        public String getAsText() {
            Organisation organisation = (Organisation)this.getValue();
            if(organisation == null) {
                return "";
            } else {
                return Integer.toString(organisation.getId());
            }
        }

}
