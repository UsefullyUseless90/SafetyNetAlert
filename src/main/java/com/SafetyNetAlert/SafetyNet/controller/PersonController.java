package com.SafetyNetAlert.SafetyNet.controller;


import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.service.PersonService;
import org.json.JSONException;
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
        personService.createPerson(person);
        ResponseEntity<Person> creation = ResponseEntity.status(HttpStatus.CREATED).body(person);
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
        List<Person> personList = personService.getAllPerson();
        ResponseEntity<List<Person>> result = ResponseEntity.status(HttpStatus.OK).body(personList);
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
        List<Person> updatedPerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    /**
     * Delete a person from the record
     * @param person
     * @return
     * @throws IOException
     */

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestBody Person person) throws IOException {
        personService.deletePerson(person);

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted!");
    }

}


