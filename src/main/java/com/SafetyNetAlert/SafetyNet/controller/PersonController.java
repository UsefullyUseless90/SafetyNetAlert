package com.SafetyNetAlert.SafetyNet.controller;


import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    private Logger logger = LogManager.getLogger(PersonController.class);
    /**
     * Create / Add new Person
     *
     * @param person
     * @return creation
     * @throws IOException
     * @throws JSONException
     */

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws IOException, JSONException {
        logger.info("Creating Person please wait...");
        personService.createPerson(person);
        ResponseEntity<Person> creation = ResponseEntity.status(HttpStatus.CREATED).body(person);
        logger.info("Person Created:" + creation);
        return creation;
    }

    /**
     * Read / Get all Persons
     *
     * @return results
     * @throws JSONException
     * @throws IOException
     */

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson() throws JSONException, IOException {
        logger.info("Checking, please wait...");
        List<Person> personList = personService.getAllPerson();
        ResponseEntity<List<Person>> result = ResponseEntity.status(HttpStatus.OK).body(personList);
        logger.info("Persons list:" + result);
        return result;
    }

    /**
     * Update an existing person
     *
     * @param person
     * @throws IOException
     * @throws JSONException
     * @return
     */

    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody Person person) throws IOException, JSONException {
        logger.info("Checking, please wait...");
        List<Person> updatedPerson = personService.updatePerson(person);
        logger.info("Person updated!" + updatedPerson);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    /**
     * Delete a person from the record
     * @param person
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<?> deletePerson(@RequestBody Person person) throws IOException {
        logger.info("Checking, please wait...");
        personService.deletePerson(person);
        logger.info("Successfully Deleted!" + person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

}


