package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DataJsonTest {

    @Test
    void testConstructor() {
        DataJson actualDataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        actualDataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        actualDataJson.setMedicalrecords(medicalRecordList);
        ArrayList<Person> personList = new ArrayList<>();
        actualDataJson.setPersons(personList);
        List<FireStation> firestations = actualDataJson.getFirestations();
        assertSame(fireStationList, firestations);
        assertEquals(medicalRecordList, firestations);
        assertEquals(personList, firestations);
        List<MedicalRecord> medicalrecords = actualDataJson.getMedicalrecords();
        assertSame(medicalRecordList, medicalrecords);
        assertEquals(firestations, medicalrecords);
        List<Person> persons = actualDataJson.getPersons();
        assertEquals(persons, medicalrecords);
        assertSame(personList, persons);
        assertEquals(medicalRecordList, persons);
        assertEquals(firestations, persons);
    }
}

