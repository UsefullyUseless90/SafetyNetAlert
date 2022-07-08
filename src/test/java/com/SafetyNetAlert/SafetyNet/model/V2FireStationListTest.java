package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class V2FireStationListTest {

    @Test
    void testConstructor() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        assertEquals(fireStationList, (new V2FireStationList(dataJson)).getStations());
    }

    @Test
    void testConstructorFirestation() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(medicalRecordList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorEmptyLoop() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        assertEquals(fireStationList, (new V2FireStationList(dataJson)).getStations());
    }

    @Test
    void testConstructorFirestationLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(medicalRecordList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFirestationList() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation1);
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(medicalRecordList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFirestationListLoopA() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("Station");
        fireStation1.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation1);
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(2, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(1);
        assertEquals(medicalRecordList, getResult1.getPersonList());
        V2Family getResult2 = familyList.get(0);
        assertEquals(medicalRecordList, getResult2.getPersonList());
        assertEquals(0, getResult2.getHouseHoldMembers());
        assertEquals(0, getResult2.getChildren());
        assertEquals(0, getResult2.getAdults());
        assertEquals("Station", getResult2.getAddress());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
        assertEquals("42 Main St", getResult1.getAddress());
    }

    @Test
    void testConstructorFirestationListLoopB() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("42");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation1);
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(2, stations.size());
        V2FireStation getResult = stations.get(0);
        assertEquals("42", getResult.getId());
        V2FireStation getResult1 = stations.get(1);
        assertEquals("Station", getResult1.getId());
        List<V2Family> familyList = getResult1.getFamilyList();
        assertEquals(1, familyList.size());
        List<V2Family> familyList1 = getResult.getFamilyList();
        assertEquals(1, familyList1.size());
        V2Family getResult2 = familyList.get(0);
        assertEquals(medicalRecordList, getResult2.getPersonList());
        V2Family getResult3 = familyList1.get(0);
        assertEquals(medicalRecordList, getResult3.getPersonList());
        assertEquals(0, getResult3.getHouseHoldMembers());
        assertEquals(0, getResult3.getChildren());
        assertEquals(0, getResult3.getAdults());
        assertEquals("42 Main St", getResult3.getAddress());
        assertEquals(0, getResult2.getHouseHoldMembers());
        assertEquals(0, getResult2.getChildren());
        assertEquals(0, getResult2.getAdults());
        assertEquals("42 Main St", getResult2.getAddress());
    }

    @Test
    void testConstructorMedicalRecord() throws IOException {
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
        assertEquals(fireStationList, (new V2FireStationList(dataJson)).getStations());
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
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        assertEquals(fireStationList, (new V2FireStationList(dataJson)).getStations());
    }

    @Test
    void testConstructorFirestationPlusMedicalRecord() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(medicalRecordList);
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(personList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFireStationPlusPerson() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

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
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(1, getResult1.getPersonList().size());
        assertEquals(1, getResult1.getHouseHoldMembers());
        assertEquals(1, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFirestationPerson() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        Person person = new Person();
        person.setAddress("Doe");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(medicalRecordList, getResult1.getPersonList());
        assertEquals(0, getResult1.getHouseHoldMembers());
        assertEquals(0, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFirestationPersonLoopA() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

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
        person1.setFirstName("First Name");
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(2, getResult1.getPersonList().size());
        assertEquals(2, getResult1.getHouseHoldMembers());
        assertEquals(2, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFirestationPersonLoopB() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

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
        person1.setLastName("Last Name");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(2, getResult1.getPersonList().size());
        assertEquals(2, getResult1.getHouseHoldMembers());
        assertEquals(2, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorPersonPlusMedical() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

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
        person.setFirstName("42 Main St");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(1, getResult1.getPersonList().size());
        assertEquals(1, getResult1.getHouseHoldMembers());
        assertEquals(1, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }

    @Test
    void testConstructorFireStationPersonMedical() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

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
        person.setLastName("42 Main St");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        List<V2FireStation> stations = (new V2FireStationList(dataJson)).getStations();
        assertEquals(1, stations.size());
        V2FireStation getResult = stations.get(0);
        List<V2Family> familyList = getResult.getFamilyList();
        assertEquals(1, familyList.size());
        assertEquals("Station", getResult.getId());
        V2Family getResult1 = familyList.get(0);
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals(1, getResult1.getPersonList().size());
        assertEquals(1, getResult1.getHouseHoldMembers());
        assertEquals(1, getResult1.getChildren());
        assertEquals(0, getResult1.getAdults());
    }
}

