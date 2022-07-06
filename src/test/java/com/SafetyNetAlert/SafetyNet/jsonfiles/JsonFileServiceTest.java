package com.SafetyNetAlert.SafetyNet.jsonfiles;

import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {JsonFileService.class})
@ExtendWith(SpringExtension.class)
class JsonFileServiceTest {
    @Mock
    File file;
    @InjectMocks
    JsonFileService jsonFileService;

    Person person = new Person();
    FireStation firestation = new FireStation();
    MedicalRecord medicalRecord = new MedicalRecord();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        person.setLastName("Scott");
        person.setFirstName("Michael");
        person.setPhone("555-6935-9866");
        person.setZip("57787");
        person.setAddress("78 3rd Street");
        person.setCity("Scranton");
        person.setEmail("MichaelScott@Dunder&Mifflin");

        firestation.setAddress("87 3rd Street");
        firestation.setStation("89");

        medicalRecord.setFirstName("Michael");
        medicalRecord.setLastName("Scott");
        medicalRecord.setBirthdate("04/02/1986");
        List<String>medicationsList = new ArrayList<>();
        medicationsList.add("aznol:600mg");
        medicationsList.add("hydrapermazol:300mg");
        medicalRecord.setMedications(medicationsList);
        List<String>allergiesList = new ArrayList<>();
        allergiesList.add("peanuts");
        medicalRecord.setAllergies(allergiesList);


    }

    @Test
    void testJsonReaderService() throws IOException {
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        List<FireStation> firestations = new ArrayList<>();
        firestations.add(firestation);
        List<MedicalRecord> recordList = new ArrayList<>();
        recordList.add(medicalRecord);
        assertEquals(1, firestations.size());
        assertEquals(1, personList.size());
        assertEquals(1, recordList.size());
        FireStation getResult = firestations.get(0);
        assertEquals("89", getResult.getStation());
        MedicalRecord getResult1 = recordList.get(0);
        assertEquals(medicalRecord.getMedications(), getResult1.getMedications());
        assertEquals("Scott",getResult1.getLastName());
        assertEquals("Michael",getResult1.getFirstName());
        assertEquals("04/02/1986",getResult1.getBirthdate());
        assertEquals(medicalRecord.getAllergies(),getResult1.getAllergies());
        assertEquals("87 3rd Street", getResult.getAddress());
    }

    @Test
    void testJsonWriterService() throws IOException {
        jsonFileService.jsonWriterService(new DataJson());
    }

    @Test
    void testUpdatePersons() throws IOException {
        jsonFileService.updatePersons(Collections.singletonList(new Person()));
    }

    @Test
    void testUpdateStations() throws IOException {
        jsonFileService.updateStations(Collections.singletonList(new FireStation()));
    }

    @Test
    void testUpdateRecords() throws IOException {
        jsonFileService.updateRecords(Collections.singletonList(new MedicalRecord()));
    }
}

