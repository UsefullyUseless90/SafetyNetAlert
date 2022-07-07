package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ServiceUrlsImpl.class})
@ExtendWith(SpringExtension.class)
class ServiceUrlsImplTest {
    @MockBean
    private JsonFileService jsonFileService;

    @Autowired
    private ServiceUrlsImpl serviceUrlsImpl;

    @Test
    void testChildAlertEmptyVersion() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.childAlert("42 Main St");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlertMedicalAssign() throws IOException {
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
        this.serviceUrlsImpl.childAlert("42 Main St");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlertPerson() throws IOException {
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
        this.serviceUrlsImpl.childAlert("42 Main St");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlertFail() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.childAlert("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlertPersonList() throws IOException {
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
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.childAlert("42 Main St");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlertSetters() throws IOException {
        Person person = mock(Person.class);
        when(person.getAddress()).thenReturn("42 Main St");
        when(person.getEmail()).thenReturn("jane.doe@example.org");
        when(person.getFirstName()).thenReturn("Jane");
        when(person.getLastName()).thenReturn("Doe");
        when(person.getPhone()).thenReturn("4105551212");
        doNothing().when(person).setAddress((String) any());
        doNothing().when(person).setCity((String) any());
        doNothing().when(person).setEmail((String) any());
        doNothing().when(person).setFirstName((String) any());
        doNothing().when(person).setLastName((String) any());
        doNothing().when(person).setPhone((String) any());
        doNothing().when(person).setZip((String) any());
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        ChildList actualChildAlertResult = this.serviceUrlsImpl.childAlert("42 Main St");
        List<ChildAlert> childList = actualChildAlertResult.getChildList();
        assertEquals(1, childList.size());
        assertEquals(fireStationList, actualChildAlertResult.getOtherMembers());
        ChildAlert getResult = childList.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals(-1, getResult.getAge());
        verify(this.jsonFileService).jsonReaderService();
        verify(person).getAddress();
        verify(person).getEmail();
        verify(person).getFirstName();
        verify(person).getLastName();
        verify(person).getPhone();
        verify(person).setAddress((String) any());
        verify(person).setCity((String) any());
        verify(person).setEmail((String) any());
        verify(person).setFirstName((String) any());
        verify(person).setLastName((String) any());
        verify(person).setPhone((String) any());
        verify(person).setZip((String) any());
    }

    @Test
    void testChildAlertError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.childAlert("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberFirestation() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("4");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.phoneNumber("4");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberEmpty() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.phoneNumber("3");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberFirestationLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("4");

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(fireStationList);
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.phoneNumber("5");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberPerson() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("1");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberPersonLoop() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("5");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.phoneNumber("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberStationList() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("3,1");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumberStationPlusRecord() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("90");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberStationPlusPerson() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("90");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberStationPersonMedic() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("90");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("90");

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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertEquals(personList, this.serviceUrlsImpl.phoneNumber("90").getPhoneNumber());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberStationListPlusPerson() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("Station");
        doNothing().when(fireStation).setAddress((String) any());
        doNothing().when(fireStation).setStation((String) any());
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("90");

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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation, atLeast(1)).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberPersonsStation() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("90");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberStationsListPlusPersonList() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("90");
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
        List<String> phoneNumber = this.serviceUrlsImpl.phoneNumber("90").getPhoneNumber();
        assertEquals(1, phoneNumber.size());
        assertEquals("4105551212", phoneNumber.get(0));
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumberError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.phoneNumber("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressEmpty() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.fireAddress("address").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressFirestation() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("address").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressMedicalRecord() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("address").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressPerson() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("address").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.fireAddress("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressFireStationList() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddressFirestationMedic() throws IOException {
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
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
    }

    @Test
    void testFireAddressFireStationPerson() throws IOException {
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertEquals("42 Main St", person.getAddress());
    }

    @Test
    void testFireAddressFireStationListData() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        FireStation fireStation1 = mock(FireStation.class);
        when(fireStation1.getAddress()).thenReturn("foo");
        when(fireStation1.getStation()).thenReturn("Station");
        doNothing().when(fireStation1).setAddress((String) any());
        doNothing().when(fireStation1).setStation((String) any());
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFireAddressFirestationListLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        FireStation fireStation1 = mock(FireStation.class);
        when(fireStation1.getAddress()).thenReturn("42 Main St");
        when(fireStation1.getStation()).thenReturn("foo");
        doNothing().when(fireStation1).setAddress((String) any());
        doNothing().when(fireStation1).setStation((String) any());
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
        assertTrue(this.serviceUrlsImpl.fireAddress("Oxford").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFireAddressError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.fireAddress("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationEmpty() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFirestation() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationMedicalRecord() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationPerson() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.floodStation(new ArrayList<>()));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFirestationList() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFirestationMedicalRecords() throws IOException {
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
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFirestationPerson() throws IOException {
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
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStationFirestationLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        FireStation fireStation1 = mock(FireStation.class);
        when(fireStation1.getAddress()).thenReturn("foo");
        when(fireStation1.getStation()).thenReturn("Station");
        doNothing().when(fireStation1).setAddress((String) any());
        doNothing().when(fireStation1).setStation((String) any());
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFloodStationFirestationListLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        FireStation fireStation1 = mock(FireStation.class);
        when(fireStation1.getAddress()).thenReturn("42 Main St");
        when(fireStation1.getStation()).thenReturn("foo");
        doNothing().when(fireStation1).setAddress((String) any());
        doNothing().when(fireStation1).setStation((String) any());
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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFloodStationFirestationPersonLoop() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation("Station");
        FireStation fireStation1 = mock(FireStation.class);
        when(fireStation1.getAddress()).thenReturn("foo");
        when(fireStation1.getStation()).thenReturn("Station");
        doNothing().when(fireStation1).setAddress((String) any());
        doNothing().when(fireStation1).setStation((String) any());
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation("Station");

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
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFloodStationError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.floodStation(new ArrayList<>()));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfoEmpty() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.personInfo("Jane", "Doe").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    /**
     * Method under test: {@link ServiceUrlsImpl#personInfo(String, String)}
     */
    @Test
    void testPersonInfoMedicalRecord() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.personInfo("Jane", "Doe").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfoPerson() throws IOException {
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
        assertEquals(1, this.serviceUrlsImpl.personInfo("Jane", "Doe").size());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfoFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.personInfo("Jane", "Doe"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfoPersonList() throws IOException {
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
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertEquals(2, this.serviceUrlsImpl.personInfo("Jane", "Doe").size());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfoPersonData() throws IOException {
        Person person = mock(Person.class);
        when(person.getAddress()).thenReturn("42 Main St");
        when(person.getEmail()).thenReturn("jane.doe@example.org");
        when(person.getFirstName()).thenReturn("Jane");
        when(person.getLastName()).thenReturn("foo");
        when(person.getPhone()).thenReturn("4105551212");
        doNothing().when(person).setAddress((String) any());
        doNothing().when(person).setCity((String) any());
        doNothing().when(person).setEmail((String) any());
        doNothing().when(person).setFirstName((String) any());
        doNothing().when(person).setLastName((String) any());
        doNothing().when(person).setPhone((String) any());
        doNothing().when(person).setZip((String) any());
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
        assertTrue(this.serviceUrlsImpl.personInfo("Jane", "Doe").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(person).getAddress();
        verify(person).getEmail();
        verify(person).getFirstName();
        verify(person).getLastName();
        verify(person).getPhone();
        verify(person).setAddress((String) any());
        verify(person).setCity((String) any());
        verify(person).setEmail((String) any());
        verify(person).setFirstName((String) any());
        verify(person).setLastName((String) any());
        verify(person).setPhone((String) any());
        verify(person).setZip((String) any());
    }

    @Test
    void testCommunityEmailCityParameters() throws IOException {
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
        assertTrue(!this.serviceUrlsImpl.communityEmail("Oxford").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmailDataJson() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.communityEmail("Oxford").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmailParametersUrl() throws IOException {
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
        assertTrue(!this.serviceUrlsImpl.communityEmail("Oxford").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmailPersonList() throws IOException {
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
        List<CommunityEmail> actualCommunityEmailResult = this.serviceUrlsImpl.communityEmail("Oxford");
        assertEquals(1, actualCommunityEmailResult.size());
        assertEquals("jane.doe@example.org", actualCommunityEmailResult.get(0).getEmail());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmailError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.communityEmail("Paris"));
        verify(this.jsonFileService).jsonReaderService();
    }
}

