package com.pibox.knwh.service;

import com.pibox.knwh.entity.Person;

import java.util.List;

public interface PersonService{

    List<Person> getAllPeople();

    void addPerson(Person person);

    void deletePerson(Long personId);
}
