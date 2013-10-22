package com.blung.sample.service.impl;

import com.blung.sample.domain.Person;
import com.blung.sample.service.PersonService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: saifulbahri
 * Date: 10/22/13
 * Time: 11:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("personService")
@Transactional
public class PersonImpl implements PersonService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    @Override
    public void delete(Person person) {
        if(person!=null){
            sessionFactory.getCurrentSession().delete(person);
        }
    }

    @Override
    public void deleteMore(Person[] persons) {
        if(persons!=null){
            for(Person person:persons)
                delete(person);
        }
    }

    @Override
    public Person findPerson(Long id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class,id);
    }

    @Override
    public List<Person> findPersons() {
        return sessionFactory.getCurrentSession().createQuery("from Person o " +
                "order by o.id").list();
    }

    @Override
    public List<Person> findPersons(Person person) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);

        if (person.getId() != null)
            criteria = criteria.add(Restrictions.eq("id", person.getId()));

        return criteria.addOrder(Order.asc("id")).list();
    }

    @Override
    public Long countPersons() {
        return (Long) sessionFactory.getCurrentSession().createQuery("select count(o) from Person o").uniqueResult();
    }

    
}
