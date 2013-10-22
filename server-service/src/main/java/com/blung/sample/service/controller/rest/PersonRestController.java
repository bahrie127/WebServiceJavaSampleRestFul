package com.blung.sample.service.controller.rest;


import com.blung.sample.domain.Person;
import com.blung.sample.service.PersonService;
import com.blung.sample.service.editor.PersonEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: saifulbahri
 * Date: 9/21/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/person")
public class PersonRestController {

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    /*
     * *** Service Method *******************************************************************************************
     */

    @RequestMapping(value = "/count", method = RequestMethod.GET, headers = "Accept=application/json")
    public
    @ResponseBody
    long count() {
        try {
            return personService.countPersons();
        } catch (DataAccessException e) {
            return 0L;
        }
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public
    @ResponseBody
    List<Person> findAll() {
        try {
            return personService.findPersons();
        } catch (DataAccessException e) {
            return new ArrayList<Person>();
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "id", headers = "Accept=application/json")
    public
    @ResponseBody
    Person findById(@RequestParam("id") Person person) {
        return person;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    List<Person> find(@RequestBody Person person) {
        try {
            return personService.findPersons(person);
        } catch (DataAccessException e) {
            return new ArrayList<Person>();
        }
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<Object, Object> save(@RequestBody Person person) {
        if (person == null) throw new IllegalArgumentException("A Person is required");
        Map<Object, Object> result = new HashMap<Object, Object>();
        try {
            personService.save(person);
            result.put("success", true);
            result.put("data", person);
            result.put("message", "Sukses Save Person " + person);
            result.put("status", "oke");
        } catch (DataAccessException e) {
            result.put("success", false);
            result.put("data", person);
            result.put("exception", e.getMessage());
            result.put("message", "Gagal Save Person " + person);
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("id") Person person) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            personService.delete(person);
            result.put("success", true);
            result.put("message", "Sukses Delete Person " + person);
        } catch (DataAccessException e) {
            result.put("success", false);
            result.put("exception", e.getMessage());
            result.put("message", "Gagal Delete Person " + person);
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> deleteMore(@RequestBody Person[] countries) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            personService.deleteMore(countries);
            result.put("success", true);
            result.put("message", "Sukses Delete Person " + countries);
        } catch (DataAccessException e) {
            result.put("success", false);
            result.put("exception", e.getMessage());
            result.put("message", "Gagal Delete Person " + countries);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> update(@RequestBody Person person) {
        if (person == null) throw new IllegalArgumentException("A Product is required");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            personService.save(person);
            result.put("success", true);
            result.put("data", person);
            result.put("message", "Sukses Update Person " + person);
        } catch (DataAccessException e) {
            result.put("success", false);
            result.put("data", person);
            result.put("exception", e.getMessage());
            result.put("message", "Gagal update person " + person);
        }
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Person.class, new PersonEditor(personService));
    }
}
