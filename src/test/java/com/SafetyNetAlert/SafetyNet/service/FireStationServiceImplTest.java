package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.*;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class FireStationServiceImplTest {

    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @Mock
    FireStation station;
    @InjectMocks
    FireStationServiceImpl fireStationServiceImpl;



    @Test
    void testCreateStation() throws IOException, JSONException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        List<FireStation> actualCreateStationResult = this.fireStationServiceImpl.createStation(fireStation);
        assertSame(fireStationList, actualCreateStationResult);
        assertEquals(1, actualCreateStationResult.size());
        FireStation getResult = actualCreateStationResult.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals("Station", getResult.getStation());
        verify(this.jsonFileService, atLeast(1)).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testCreateStation2() throws IOException, JSONException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        assertThrows(IOException.class, () -> this.fireStationServiceImpl.createStation(fireStation));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStation() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.getAllStation();
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }

    @Test
    void testGetAllStation2() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        List<FireStation> actualAllStation = this.fireStationServiceImpl.getAllStation();
        assertSame(fireStationList, actualAllStation);
        assertTrue(actualAllStation.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testGetAllStation3() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.fireStationServiceImpl.getAllStation());
        verify(this.jsonFileService).jsonReaderService();
    }


    @Test
    void testUpdateStation() throws IOException {

        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.updateStation(new FireStation());
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }

    @Test
    void testUpdateStation2() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        List<FireStation> actualUpdateStationResult = this.fireStationServiceImpl.updateStation(fireStation);
        assertSame(fireStationList, actualUpdateStationResult);
        assertTrue(actualUpdateStationResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testUpdateStation3() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        assertThrows(IOException.class, () -> this.fireStationServiceImpl.updateStation(fireStation));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testUpdateStation4() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");
        List<FireStation> actualUpdateStationResult = this.fireStationServiceImpl.updateStation(fireStation1);
        assertSame(fireStationList, actualUpdateStationResult);
        assertEquals(1, actualUpdateStationResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testUpdateStation5() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("foo");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");
        List<FireStation> actualUpdateStationResult = this.fireStationServiceImpl.updateStation(fireStation1);
        assertSame(fireStationList, actualUpdateStationResult);
        assertEquals(1, actualUpdateStationResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
        verify(fireStation).getAddress();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testDeleteStation() throws IOException {

        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<FireStation> result = fireStationServiceImpl.deleteStation(new FireStation());
        result.add(station);
        Assertions.assertEquals(Collections.singletonList(station), result);
    }


    @Test
    void testDeleteStation2() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        List<FireStation> actualDeleteStationResult = this.fireStationServiceImpl.deleteStation(fireStation);
        assertSame(fireStationList, actualDeleteStationResult);
        assertTrue(actualDeleteStationResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testDeleteStation3() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        assertThrows(IOException.class, () -> this.fireStationServiceImpl.deleteStation(fireStation));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testDeleteStation4() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");
        List<FireStation> actualDeleteStationResult = this.fireStationServiceImpl.deleteStation(fireStation1);
        assertSame(fireStationList, actualDeleteStationResult);
        assertTrue(actualDeleteStationResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
    }

    @Test
    void testDeleteStation5() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("foo");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");
        List<FireStation> actualDeleteStationResult = this.fireStationServiceImpl.deleteStation(fireStation1);
        assertSame(fireStationList, actualDeleteStationResult);
        assertEquals(1, actualDeleteStationResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testDeleteStation6() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("foo");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        doNothing().when(this.jsonFileService).updateStations((List<FireStation>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");
        List<FireStation> actualDeleteStationResult = this.fireStationServiceImpl.deleteStation(fireStation1);
        assertSame(fireStationList, actualDeleteStationResult);
        assertEquals(1, actualDeleteStationResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updateStations((List<FireStation>) any());
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        dataJson.setFirestations(fireStationList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData3() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData4() throws IOException {
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData5() throws IOException {
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData6() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.fireStationServiceImpl.filteredData("42"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData7() throws IOException {
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
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFilteredData8() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("42");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        StationNumber actualFilteredDataResult = this.fireStationServiceImpl.filteredData("42");
        assertEquals(0, actualFilteredDataResult.getAdults());
        assertEquals(medicalRecordList, actualFilteredDataResult.getPersonList());
        assertEquals(0, actualFilteredDataResult.getChildren());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData9() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
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
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData10() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData11() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("42");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        StationNumber actualFilteredDataResult = this.fireStationServiceImpl.filteredData("42");
        assertEquals(0, actualFilteredDataResult.getAdults());
        List<CoveragePerson> personList1 = actualFilteredDataResult.getPersonList();
        assertEquals(1, personList1.size());
        assertEquals(1, actualFilteredDataResult.getChildren());
        CoveragePerson getResult = personList1.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals("4105551212", getResult.getPhoneNumber());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData12() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
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
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData13() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("42 Main St");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation1);
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation, atLeast(1)).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testFilteredData14() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("Doe");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.fireStationServiceImpl.filteredData("42");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

}
