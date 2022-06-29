package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.Person;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private JsonFileService jsonFileService;


    /**
     * @param person
     * @return an updated list of all the persons
     * @throws IOException
     * @throws JSONException
     */

    @Override
    public List<Person> createPerson(Person person) throws IOException{
        List<Person> personList = this.getAllPerson();
        person.setLastName(person.getLastName());
        person.setFirstName(person.getFirstName());
        person.setPhone(person.getPhone());
        person.setZip(person.getZip());
        person.setAddress(person.getAddress());
        person.setCity(person.getCity());
        person.setEmail(person.getEmail());
        personList.add(person);

        jsonFileService.updatePersons(personList);

        return this.getAllPerson();
    }

    /**
     *
     * @return
     * @throws IOException
     */

    @Override
    public List<Person> getAllPerson() throws IOException {
        List<Person> culvertPeopleList = jsonFileService.jsonReaderService().getPersons();
        return culvertPeopleList;
    }

    /**
     *
     * @param person
     * @return
     * @throws IOException
     */

    @Override
    public List<Person> updatePerson(Person person) throws IOException{
        // Creates a list and add to it the persons
        List<Person> personList = this.getAllPerson();

        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            // In case of any match the value is replaced by a new one
            if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {
                personList.set(i, person);
                break;
            }
        }

        // Writing in json file.
        this.jsonFileService.updatePersons(personList);

        return personList;
    }

    /**
     *
     * @param person
     * @return
     * @throws IOException
     */

    @Override
    public List<Person> deletePerson(Person person) throws IOException {
        // Creates a list and add to it the persons.
        List<Person> personList = this.getAllPerson();

        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            // In case of any match the value is deleted.
            if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {
                personList.remove(i);
                break;
            }
        }

        // Writing in json file.
        this.jsonFileService.updatePersons(personList);

        return personList;
    }

}