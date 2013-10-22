package com.blung.sample.service;

import com.blung.sample.domain.Person;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: saifulbahri
 * Date: 10/22/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonService {
    public void save(Person person);

    public void delete(Person person);

    public void deleteMore(Person[] persons);

    public Person findPerson(Long id);

    public List<Person> findPersons();

    public List<Person> findPersons(Person person);

    public Long countPersons();
}
