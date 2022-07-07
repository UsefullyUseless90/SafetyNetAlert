package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V2FamilyListTest {

    @Test
    void testConstructorEmpty() throws IOException {

        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        assertEquals(fireStationList, (new V2FamilyList(dataJson)).getFamilies());

    }

    @Test
    void testConstructorMedicalRecords() throws IOException {

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        assertEquals(fireStationList, (new V2FamilyList(dataJson)).getFamilies());

    }

    @Test
    void testConstructorPerson() throws IOException {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2Family> families = (new V2FamilyList(dataJson)).getFamilies();
        assertEquals(1, families.size());
        V2Family getResult = families.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals(1, getResult.getPersonList().size());
        assertEquals(1, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }

    @Test
    void testConstructorPersonList() throws IOException {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        Person person1 = new Person();
        person1.setAddress("42 Main St");
        person1.setCity("Oxford");
        person1.setEmail("jane.doe@example.org");
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2Family> families = (new V2FamilyList(dataJson)).getFamilies();
        assertEquals(1, families.size());
        V2Family getResult = families.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals(2, getResult.getPersonList().size());
        assertEquals(2, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }

    @Test
    void testConstructorGetResult() throws IOException {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        Person person1 = new Person();
        person1.setAddress("Address");
        person1.setCity("Oxford");
        person1.setEmail("jane.doe@example.org");
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2Family> families = (new V2FamilyList(dataJson)).getFamilies();
        assertEquals(2, families.size());
        V2Family getResult = families.get(0);
        assertEquals(1, getResult.getPersonList().size());
        V2Family getResult1 = families.get(1);
        assertEquals(1, getResult1.getPersonList().size());
        assertEquals(1, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(1, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
        assertEquals("Address", getResult.getAddress());
    }

    @Test
    void testConstructorMedicalRecordPerson() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Doe");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        List<V2Family> families = (new V2FamilyList(dataJson)).getFamilies();
        assertEquals(1, families.size());
        V2Family getResult = families.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals(1, getResult.getPersonList().size());
        assertEquals(1, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }

    @Test
    void testConstructorMedicalRecordsListPlusPerson() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Jane");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        List<V2Family> families = (new V2FamilyList(dataJson)).getFamilies();
        assertEquals(1, families.size());
        V2Family getResult = families.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals(1, getResult.getPersonList().size());
        assertEquals(1, getResult.getHouseHoldMembers());
        assertEquals(0, getResult.getChildren());
        assertEquals(0, getResult.getAdults());
    }
}

