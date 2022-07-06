package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonInfoTest {

    PersonInfo personInfo = new PersonInfo();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<String> meds = new ArrayList<>();
        meds.add("Aspirin");
        List<String> allergies = new ArrayList<>();
        allergies.add("peanuts");
        personInfo.setLastName("Scott");
        personInfo.setFirstName("Michael");
        personInfo.setAge(57);
        personInfo.setAddress("78 3rd Street");
        personInfo.setEmail("blahblah@blegh.com");
        personInfo.setAllergies(allergies);
        personInfo.setMedications(meds);

    }

    @Test
    void testConstructor() {

        assertEquals("78 3rd Street", this.personInfo.getAddress());
        List<String> medications = this.personInfo.getMedications();
        List<String> allergies = this.personInfo.getAllergies();
        assertEquals(medications, personInfo.getMedications());
        assertEquals("Scott",personInfo.getLastName());
        assertEquals("Michael",personInfo.getFirstName());
        assertEquals("blahblah@blegh.com",personInfo.getEmail());
        assertEquals(allergies, personInfo.getAllergies());
        assertEquals(57, personInfo.getAge());
    }
}

