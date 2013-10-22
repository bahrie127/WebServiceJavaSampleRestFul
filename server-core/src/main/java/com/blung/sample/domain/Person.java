package com.blung.sample.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: saifulbahri
 * Date: 9/7/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "name",length = 50,nullable = false)
    private String name;

    @Column(name = "address",length = 100,nullable = false)
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
