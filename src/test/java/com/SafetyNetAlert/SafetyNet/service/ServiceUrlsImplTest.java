package com.SafetyNetAlert.SafetyNet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.ChildAlert;
import com.SafetyNetAlert.SafetyNet.model.ChildList;
import com.SafetyNetAlert.SafetyNet.model.CommunityEmail;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ServiceUrlsImpl.class})
@ExtendWith(SpringExtension.class)
class ServiceUrlsImplTest {
    @MockBean
    private JsonFileService jsonFileService;

    @Autowired
    private ServiceUrlsImpl serviceUrlsImpl;

    @Test
    void testChildAlert() throws IOException {
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
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert3() throws IOException {
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
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert4() throws IOException {
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
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert5() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.childAlert("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert6() throws IOException {
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
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert7() throws IOException {
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
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.childAlert("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testChildAlert8() throws IOException {
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
    void testPhoneNumber() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber3() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber4() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber5() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber6() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.phoneNumber("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber7() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPhoneNumber8() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber9() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber10() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("https://example.org/example");
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
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertEquals(personList, this.serviceUrlsImpl.phoneNumber("https://example.org/example").getPhoneNumber());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber11() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber12() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation, atLeast(1)).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber13() throws IOException {
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
        this.serviceUrlsImpl.phoneNumber("https://example.org/example");
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation).getAddress();
        verify(fireStation).getStation();
        verify(fireStation).setAddress((String) any());
        verify(fireStation).setStation((String) any());
    }

    @Test
    void testPhoneNumber14() throws IOException {
        FireStation fireStation = mock(FireStation.class);
        when(fireStation.getAddress()).thenReturn("42 Main St");
        when(fireStation.getStation()).thenReturn("https://example.org/example");
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
        List<String> phoneNumber = this.serviceUrlsImpl.phoneNumber("https://example.org/example").getPhoneNumber();
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
    void testFireAddress() throws IOException {
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
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress3() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress4() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress5() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress6() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.fireAddress("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress7() throws IOException {
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
    void testFireAddress8() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress9() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFireAddress10() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(fireStation1).getAddress();
        verify(fireStation1).getStation();
        verify(fireStation1).setAddress((String) any());
        verify(fireStation1).setStation((String) any());
    }

    @Test
    void testFireAddress11() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.fireAddress("https://example.org/example").isEmpty());
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
    void testFloodStation() throws IOException {
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
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStation2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.floodStation(new ArrayList<>()).isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStation3() throws IOException {
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
    void testFloodStation4() throws IOException {
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
    void testFloodStation5() throws IOException {
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
    void testFloodStation6() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.floodStation(new ArrayList<>()));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testFloodStation7() throws IOException {
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
    void testFloodStation8() throws IOException {
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
    void testFloodStation9() throws IOException {
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
    void testFloodStation10() throws IOException {
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
    void testFloodStation11() throws IOException {
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
    void testFloodStation12() throws IOException {
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
    void testPersonInfo() throws IOException {
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
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.personInfo("Jane", "Doe").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfo2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.personInfo("Jane", "Doe").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfo3() throws IOException {
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
    void testPersonInfo4() throws IOException {
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
    void testPersonInfo5() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.personInfo("Jane", "Doe"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfo6() throws IOException {
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
    void testPersonInfo7() throws IOException {
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
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(medicalRecordList);
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertEquals(1, this.serviceUrlsImpl.personInfo("Jane", "Doe").size());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testPersonInfo8() throws IOException {
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
    void testPersonInfoError() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.personInfo("Jane", "Doe"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmail() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.communityEmail("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmail2() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(new ArrayList<>());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        assertTrue(this.serviceUrlsImpl.communityEmail("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmail3() throws IOException {
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
        assertTrue(this.serviceUrlsImpl.communityEmail("https://example.org/example").isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmail4() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.communityEmail("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testCommunityEmail5() throws IOException {
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
        assertThrows(IOException.class, () -> this.serviceUrlsImpl.communityEmail("https://example.org/example"));
        verify(this.jsonFileService).jsonReaderService();
    }
}

