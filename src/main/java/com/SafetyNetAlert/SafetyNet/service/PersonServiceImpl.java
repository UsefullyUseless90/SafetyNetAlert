package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    File file = new File("C:\\Users\\antco\\Desktop\\JAVA\\SafetyNet\\src\\main\\resources\\JsonDataSafetyNet.json");
    @Autowired
    private JsonFileService jsonFileService;


    /**
     * @param person
     * @return an updated list of all the persons
     * @throws IOException
     * @throws JSONException
     */
    @Override
    public List<Person> createPerson(Person person) throws IOException, JSONException {
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

    @Override
    public List<Person> getAllPerson() throws IOException {
        List<Person> culvertPeopleList = jsonFileService.jsonReaderService().getPersons();
        return culvertPeopleList;
    }

    @Override
    public List<Person> updatePerson(Person person) throws IOException, JSONException {
        List<Person> personList = this.getAllPerson();// create a list and add to it the persons

        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {// In case of any match the value is replaced by a new one
                personList.set(i, person);
                break;
            }
        }

        // Ecriture dnas JSON
        this.jsonFileService.updatePersons(personList);

        return personList;
    }

    @Override
    public List<Person> deletePerson(Person person) throws IOException {
        List<Person> personList = this.getAllPerson();// create a list and add to it the persons

        for (int i = 0; i < personList.size(); i++) {
            Person p = personList.get(i);
            if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {// In case of any match the value is deleted
                personList.remove(i);
                break;
            }
        }

        // Ecriture dnas JSON
        this.jsonFileService.updatePersons(personList);

        return personList;
    }

    @Override
        public List<Person> matchingAddress(Person person, FireStation fireStation) throws IOException {
            List<Person> personList = this.getAllPerson();
            List<FireStation> stationList = jsonFileService.jsonReaderService().getFirestations();
            List<Person> matchingList = new ArrayList<>();

            for (int i = 0; i < personList.size(); i++) {
                Person p = personList.get(i);
                for (int f = 0; f < stationList.size(); f++) {
                    FireStation fs = stationList.get(f);
                    if (p.getAddress().equals(person.getAddress()) && fs.getAddress().equals(fireStation.getAddress())) {// In case of any match the value is deleted
                        matchingList.add(p);
                        break;
                    }
                }
            }
            return personList;
    }
}