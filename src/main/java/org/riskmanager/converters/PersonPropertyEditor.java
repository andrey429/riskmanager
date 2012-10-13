package org.riskmanager.converters;


import org.riskmanager.domain.Person;
import org.riskmanager.service.PersonService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 13.10.12
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */
public class PersonPropertyEditor extends PropertyEditorSupport {

    private PersonService personService;

    public PersonPropertyEditor(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public String getAsText() {
        Person person = (Person) this.getValue();
        if (person == null) {
            return "";
        } else {
            return Integer.toString(person.getId());
        }

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.length() == 0) {
            setValue(null);
        } else {
            setValue(this.personService.get(Integer.parseInt(text.trim())));
        }
    }
}
