package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class V2PersonTest {
    /**
     * Method under test: default or parameterless constructor of {@link V2Person}
     */
    @Test
    void testConstructor() {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        V2Person actualV2Person = new V2Person(person);
        List<String> allergies = actualV2Person.getAllergies();
        assertTrue(allergies.isEmpty());
        assertEquals("4105551212", actualV2Person.getPhone());
        assertEquals(allergies, actualV2Person.getMedications());
        assertEquals("Doe", actualV2Person.getLastName());
        assertEquals("Jane", actualV2Person.getFirstName());
        assertEquals("jane.doe@example.org", actualV2Person.getEmail());
        assertNull(actualV2Person.getBirthdate());
    }

    /**
     * Method under test: {@link V2Person#initMedicalRecords(DataJson)}
     */
    @Test
    void testInitMedicalRecords() throws IOException {
        V2Person v2Person = new V2Person(new Person());

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        v2Person.initMedicalRecords(medicalRecordList.get(0));
        List<FireStation> firestations = dataJson.getFirestations();
        assertEquals(medicalRecordList, firestations);
        assertEquals(medicalRecordList, dataJson.getPersons());
        assertEquals(firestations, dataJson.getMedicalrecords());
        assertEquals(firestations, v2Person.getAllergies());
        assertEquals(firestations, v2Person.getMedications());
    }


    @Test
    void testInitMedicalRecords5() throws IOException {
        Person person = new Person();
        person.setFirstName("Doe");

        V2Person v2Person = new V2Person(person);
        v2Person.setLastName("Doe");

        MedicalRecord medicalRecord = new MedicalRecord();
        ArrayList<String> stringList = new ArrayList<>();
        medicalRecord.setAllergies(stringList);
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        v2Person.initMedicalRecords(medicalRecordList.get(0));
        List<FireStation> firestations = dataJson.getFirestations();
        assertEquals(personList, firestations);
        assertEquals(stringList, dataJson.getPersons());
        assertEquals(1, dataJson.getMedicalrecords().size());
        assertEquals(firestations, v2Person.getAllergies());
        assertEquals(firestations, v2Person.getMedications());
        assertEquals("Doe", v2Person.getLastName());
        assertEquals("Doe", v2Person.getFirstName());
    }

    /**
     * Method under test: {@link V2Person#initMedicalRecords(DataJson)}
     */
    @Test
    void testInitMedicalRecords6() throws IOException {
        Person person = new Person();
        person.setFirstName("Jane");

        V2Person v2Person = new V2Person(person);
        v2Person.setLastName("Jane");

        MedicalRecord medicalRecord = new MedicalRecord();
        ArrayList<String> stringList = new ArrayList<>();
        medicalRecord.setAllergies(stringList);
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        v2Person.initMedicalRecords(medicalRecordList.get(0));
        List<FireStation> firestations = dataJson.getFirestations();
        assertEquals(personList, firestations);
        assertEquals(stringList, dataJson.getPersons());
        assertEquals(1, dataJson.getMedicalrecords().size());
        assertEquals(firestations, v2Person.getAllergies());
        assertEquals(firestations, v2Person.getMedications());
        assertEquals("Jane", v2Person.getLastName());
        assertEquals("Jane", v2Person.getFirstName());
    }
}

