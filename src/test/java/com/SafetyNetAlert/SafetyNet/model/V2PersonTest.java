package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class V2PersonTest {

    Person p = new Person();
    V2Person v2 = new V2Person(p);
    MedicalRecord medicalRecord = new MedicalRecord();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<String> meds = new ArrayList<>();
        meds.add("Aspirin");
        List<String> allergies = new ArrayList<>();
        allergies.add("peanuts");
        p.setLastName("Scott");
        p.setFirstName("Michael");
        p.setPhone("4105551212");
        p.setAddress("78 3rd Street");
        p.setEmail("blahblah@blegh.com");

        v2.setLastName(p.getLastName());
        v2.setFirstName(p.getFirstName());
        v2.setAge(57);
        v2.setPhone(p.getPhone());
        v2.setEmail(p.getEmail());
        v2.setAllergies(allergies);
        v2.setMedications(meds);

        medicalRecord.setAllergies(allergies);
        medicalRecord.setBirthdate("04-02-1965");
        medicalRecord.setFirstName("Michael");
        medicalRecord.setLastName("Scott");
        medicalRecord.setMedications(meds);
    }


    @Test
    void testConstructor() {

        List<String> allergies = v2.getAllergies();
        List<String> medications = v2.getMedications();
        assertEquals(allergies, v2.getAllergies());
        LocalDate localDate = v2.getBirthdate();
        assertEquals("4105551212", v2.getPhone());
        assertEquals(medications , v2.getMedications());
        assertEquals("Scott", v2.getLastName());
        assertEquals("Michael", v2.getFirstName());
        assertEquals("blahblah@blegh.com", v2.getEmail());
        assertEquals(localDate, v2.getBirthdate());
    }

    @Test
    void testInitMedicalRecords() {
        V2Person v2Person = new V2Person(new Person());
        v2Person.setFirstName("Jane");
        v2Person.setLastName("Doe");
        v2Person.setAge(18);
        v2Person.setPhone("4105551212");
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2004-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        List<String>allergies = v2Person.getAllergies();
        List<String>medications = v2Person.getMedications();
        LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate());
        v2Person.setBirthdate(birthdate);

        assertTrue(allergies.isEmpty());
        assertTrue(medications.isEmpty());
        assertEquals(allergies, v2Person.getAllergies());
        assertEquals(medications, v2Person.getMedications());
        assertEquals("Doe", v2Person.getLastName());
        assertEquals("Jane", v2Person.getFirstName());
        assertEquals("4105551212", v2Person.getPhone());
        assertEquals(birthdate , v2Person.getBirthdate());
        assertEquals(18, v2Person.getAge());
    }
}


