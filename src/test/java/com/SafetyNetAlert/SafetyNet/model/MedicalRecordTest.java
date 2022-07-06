package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MedicalRecordTest {

    @Test
    void testConstructor() {

        MedicalRecord actualMedicalRecord = new MedicalRecord();
        ArrayList<String> stringList = new ArrayList<>();
        actualMedicalRecord.setAllergies(stringList);
        actualMedicalRecord.setBirthdate("2020-03-01");
        actualMedicalRecord.setFirstName("Jane");
        actualMedicalRecord.setLastName("Doe");
        ArrayList<String> stringList1 = new ArrayList<>();
        actualMedicalRecord.setMedications(stringList1);
        List<String> allergies = actualMedicalRecord.getAllergies();
        assertSame(stringList, allergies);
        assertEquals(stringList1, allergies);
        assertEquals("2020-03-01", actualMedicalRecord.getBirthdate());
        assertEquals("Jane", actualMedicalRecord.getFirstName());
        assertEquals("Doe", actualMedicalRecord.getLastName());
        List<String> medications = actualMedicalRecord.getMedications();
        assertSame(stringList1, medications);
        assertEquals(allergies, medications);

    }
}

