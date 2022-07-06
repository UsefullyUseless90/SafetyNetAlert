package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FireAddressTest {

    Person p = new Person();
    V2Person v2 = new V2Person(p);
    MedicalRecord medicalRecord = new MedicalRecord();
    FireAddress fireAddress = new FireAddress();

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

        // Add info to parameter Person;
        fireAddress.setStationID("1");
        fireAddress.setLastName(v2.getLastName());
        fireAddress.setFirstName(v2.getFirstName());
        fireAddress.setPhoneNumber(v2.getPhone());
        fireAddress.setAge(v2.getAge());
        fireAddress.setAddress(p.getAddress());
        fireAddress.setAllergies(medicalRecord.getAllergies());
        fireAddress.setMedications(medicalRecord.getMedications());

    }

    @Test
    void testConstructor() {
        assertEquals("78 3rd Street", this.fireAddress.getAddress());
        assertEquals("1",this.fireAddress.getStationID());
        assertEquals("4105551212",this.fireAddress.getPhoneNumber());
        List<String> medications = this.fireAddress.getMedications();
        List<String> allergies = this.fireAddress.getAllergies();
        assertEquals(medications, this.fireAddress.getMedications());
        assertEquals("Scott",this.fireAddress.getLastName());
        assertEquals("Michael", this.fireAddress.getFirstName());
        assertEquals(allergies, this.fireAddress.getAllergies());
        assertEquals(57, this.fireAddress.getAge());
    }

}

