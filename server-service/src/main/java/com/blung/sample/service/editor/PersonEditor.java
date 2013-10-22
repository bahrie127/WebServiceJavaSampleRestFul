package com.blung.sample.service.editor;

import com.blung.sample.domain.Person;
import com.blung.sample.service.PersonService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: saifulbahri
 * Date: 9/19/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonEditor extends PropertyEditorSupport {
    private PersonService personService;

    public PersonEditor(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            long id = Long.parseLong(text);

            Person result = personService.findPerson(id);

            if (result != null) {
                setValue(result);
            } else {
                throw new IllegalArgumentException("Person with id " + id + " not Found!");
            }

        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Override
    public String getAsText() {
        if (getValue() != null) {
            Person result = (Person) getValue();
            return result.getId() + "";
        } else {
            return "";
        }
    }
}
