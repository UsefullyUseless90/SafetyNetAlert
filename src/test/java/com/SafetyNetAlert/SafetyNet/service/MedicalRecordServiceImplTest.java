package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(SpringExtension.class)
class MedicalRecordServiceImplTest {

    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @InjectMocks
    MedicalRecordServiceImpl medicalRecordServiceImpl;

    MedicalRecord record;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.createRecord(new MedicalRecord());
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testCreateRecordFirestation() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        ArrayList<String> stringList = new ArrayList<>();
        medicalRecord.setMedications(stringList);
        List<MedicalRecord> actualCreateRecordResult = this.medicalRecordServiceImpl.createRecord(medicalRecord);
        assertSame(medicalRecordList, actualCreateRecordResult);
        assertEquals(1, actualCreateRecordResult.size());
        MedicalRecord getResult = actualCreateRecordResult.get(0);
        assertEquals(stringList, getResult.getAllergies());
        assertEquals(fireStationList, getResult.getMedications());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals("2020-03-01", getResult.getBirthdate());
        verify(this.jsonFileService, atLeast(1)).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testCreateRecordThrowableExceptions() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        assertThrows(IOException.class, () -> this.medicalRecordServiceImpl.createRecord(medicalRecord));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testGetAllRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.getAllRecord();
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testGetAllRecordVerify() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        List<MedicalRecord> actualAllRecord = this.medicalRecordServiceImpl.getAllRecord();
        assertSame(medicalRecordList, actualAllRecord);
        assertTrue(actualAllRecord.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testGetAllRecordFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.medicalRecordServiceImpl.getAllRecord());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testUpdateRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.updateRecord(record);
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testUpdateRecordVerify() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        List<MedicalRecord> actualUpdateRecordResult = this.medicalRecordServiceImpl.updateRecord(medicalRecord);
        assertSame(medicalRecordList, actualUpdateRecordResult);
        assertTrue(actualUpdateRecordResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testUpdateRecordThrowableException() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        assertThrows(IOException.class, () -> this.medicalRecordServiceImpl.updateRecord(medicalRecord));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testUpdateRecordMultiple() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualUpdateRecordResult = this.medicalRecordServiceImpl.updateRecord(medicalRecord1);
        assertSame(medicalRecordList, actualUpdateRecordResult);
        assertEquals(1, actualUpdateRecordResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testUpdateRecordVerifyMultiple() throws IOException {
        MedicalRecord medicalRecord = mock(MedicalRecord.class);
        when(medicalRecord.getFirstName()).thenReturn("foo");
        when(medicalRecord.getLastName()).thenReturn("Doe");
        doNothing().when(medicalRecord).setAllergies((List<String>) any());
        doNothing().when(medicalRecord).setBirthdate((String) any());
        doNothing().when(medicalRecord).setFirstName((String) any());
        doNothing().when(medicalRecord).setLastName((String) any());
        doNothing().when(medicalRecord).setMedications((List<String>) any());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualUpdateRecordResult = this.medicalRecordServiceImpl.updateRecord(medicalRecord1);
        assertSame(medicalRecordList, actualUpdateRecordResult);
        assertEquals(1, actualUpdateRecordResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        verify(medicalRecord).getFirstName();
        verify(medicalRecord).getLastName();
        verify(medicalRecord).setAllergies((List<String>) any());
        verify(medicalRecord).setBirthdate((String) any());
        verify(medicalRecord).setFirstName((String) any());
        verify(medicalRecord).setLastName((String) any());
        verify(medicalRecord).setMedications((List<String>) any());
    }

    @Test
    void testUpdateRecordVerifyLoop() throws IOException {
        MedicalRecord medicalRecord = mock(MedicalRecord.class);
        when(medicalRecord.getFirstName()).thenReturn("Jane");
        when(medicalRecord.getLastName()).thenReturn("foo");
        doNothing().when(medicalRecord).setAllergies((List<String>) any());
        doNothing().when(medicalRecord).setBirthdate((String) any());
        doNothing().when(medicalRecord).setFirstName((String) any());
        doNothing().when(medicalRecord).setLastName((String) any());
        doNothing().when(medicalRecord).setMedications((List<String>) any());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualUpdateRecordResult = this.medicalRecordServiceImpl.updateRecord(medicalRecord1);
        assertSame(medicalRecordList, actualUpdateRecordResult);
        assertEquals(1, actualUpdateRecordResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        verify(medicalRecord).getLastName();
        verify(medicalRecord).setAllergies((List<String>) any());
        verify(medicalRecord).setBirthdate((String) any());
        verify(medicalRecord).setFirstName((String) any());
        verify(medicalRecord).setLastName((String) any());
        verify(medicalRecord).setMedications((List<String>) any());
    }

    @Test
    void testDeleteRecord() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<MedicalRecord> result = medicalRecordServiceImpl.deleteRecord(record);
        result.add(record);
        Assertions.assertEquals(Collections.singletonList(record), result);
    }

    @Test
    void testDeleteRecordVerify() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        List<MedicalRecord> actualDeleteRecordResult = this.medicalRecordServiceImpl.deleteRecord(medicalRecord);
        assertSame(medicalRecordList, actualDeleteRecordResult);
        assertTrue(actualDeleteRecordResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testDeleteRecordThrowableExceptions() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        assertThrows(IOException.class, () -> this.medicalRecordServiceImpl.deleteRecord(medicalRecord));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testDeleteRecordMultiple() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualDeleteRecordResult = this.medicalRecordServiceImpl.deleteRecord(medicalRecord1);
        assertSame(medicalRecordList, actualDeleteRecordResult);
        assertTrue(actualDeleteRecordResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
    }

    @Test
    void testDeleteRecordMultipleVerify() throws IOException {
        MedicalRecord medicalRecord = mock(MedicalRecord.class);
        when(medicalRecord.getFirstName()).thenReturn("foo");
        when(medicalRecord.getLastName()).thenReturn("Doe");
        doNothing().when(medicalRecord).setAllergies((List<String>) any());
        doNothing().when(medicalRecord).setBirthdate((String) any());
        doNothing().when(medicalRecord).setFirstName((String) any());
        doNothing().when(medicalRecord).setLastName((String) any());
        doNothing().when(medicalRecord).setMedications((List<String>) any());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualDeleteRecordResult = this.medicalRecordServiceImpl.deleteRecord(medicalRecord1);
        assertSame(medicalRecordList, actualDeleteRecordResult);
        assertEquals(1, actualDeleteRecordResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        verify(medicalRecord).getFirstName();
        verify(medicalRecord).getLastName();
        verify(medicalRecord).setAllergies((List<String>) any());
        verify(medicalRecord).setBirthdate((String) any());
        verify(medicalRecord).setFirstName((String) any());
        verify(medicalRecord).setLastName((String) any());
        verify(medicalRecord).setMedications((List<String>) any());
    }

    @Test
    void testDeleteRecordMultipleVerifyLoop() throws IOException {
        MedicalRecord medicalRecord = mock(MedicalRecord.class);
        when(medicalRecord.getFirstName()).thenReturn("Jane");
        when(medicalRecord.getLastName()).thenReturn("foo");
        doNothing().when(medicalRecord).setAllergies((List<String>) any());
        doNothing().when(medicalRecord).setBirthdate((String) any());
        doNothing().when(medicalRecord).setFirstName((String) any());
        doNothing().when(medicalRecord).setLastName((String) any());
        doNothing().when(medicalRecord).setMedications((List<String>) any());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setAllergies(new ArrayList<>());
        medicalRecord1.setBirthdate("2020-03-01");
        medicalRecord1.setFirstName("Jane");
        medicalRecord1.setLastName("Doe");
        medicalRecord1.setMedications(new ArrayList<>());
        List<MedicalRecord> actualDeleteRecordResult = this.medicalRecordServiceImpl.deleteRecord(medicalRecord1);
        assertSame(medicalRecordList, actualDeleteRecordResult);
        assertEquals(1, actualDeleteRecordResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateRecords((List<MedicalRecord>) any());
        verify(medicalRecord).getLastName();
        verify(medicalRecord).setAllergies((List<String>) any());
        verify(medicalRecord).setBirthdate((String) any());
        verify(medicalRecord).setFirstName((String) any());
        verify(medicalRecord).setLastName((String) any());
        verify(medicalRecord).setMedications((List<String>) any());
    }
}

