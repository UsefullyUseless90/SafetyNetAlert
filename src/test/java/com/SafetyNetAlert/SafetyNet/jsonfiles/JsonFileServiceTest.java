package com.SafetyNetAlert.SafetyNet.jsonfiles;

import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JsonFileService.class})
@ExtendWith(SpringExtension.class)
class JsonFileServiceTest {
    @Mock
    File file;
    @InjectMocks
    JsonFileService jsonFileService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Method under test: {@link JsonFileService#jsonReaderService()}
     */
    @Test
    void testJsonReaderService() throws IOException {
        DataJson actualJsonReaderServiceResult = this.jsonFileService.jsonReaderService();
        List<FireStation> firestations = actualJsonReaderServiceResult.getFirestations();
        assertEquals(1, firestations.size());
        assertEquals(1, actualJsonReaderServiceResult.getPersons().size());
        List<MedicalRecord> medicalrecords = actualJsonReaderServiceResult.getMedicalrecords();
        assertEquals(1, medicalrecords.size());
        FireStation getResult = firestations.get(0);
        assertEquals(0, getResult.getStation());
        MedicalRecord getResult1 = medicalrecords.get(0);
        assertNull(getResult1.getMedications());
        assertNull(getResult1.getLastName());
        assertNull(getResult1.getFirstName());
        assertNull(getResult1.getBirthdate());
        assertNull(getResult1.getAllergies());
        assertNull(getResult.getAddress());
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

