package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.model.Person;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public interface PersonService {

    List<Person> createPerson(Person person) throws IOException, JSONException;

    List<Person> getAllPerson() throws JSONException, IOException;

    List<Person> updatePerson(Person person) throws IOException, JSONException;

    List<Person> deletePerson(Person person) throws IOException;

}