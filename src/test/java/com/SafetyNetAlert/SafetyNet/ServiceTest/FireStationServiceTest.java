package com.SafetyNetAlert.SafetyNet.ServiceTest;


import com.SafetyNetAlert.SafetyNet.dto.*;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.repository.FireStationRepository;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import com.SafetyNetAlert.SafetyNet.repository.PersonRepository;
import com.SafetyNetAlert.SafetyNet.service.FireStationServiceImpl;
import com.SafetyNetAlert.SafetyNet.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class FireStationServiceTest {
    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private FireStationServiceImpl fireStationService;
    @Mock
    private PersonServiceImpl personService;

    private static FireStation fireStation;
    private static List<FireStation> fireStationList = new ArrayList<>();
    private static List<Person> personList = new ArrayList<>();
    private static List<String> address = new ArrayList<>();
    private static MedicalRecord medicalRecord1 = new MedicalRecord();
    private static MedicalRecord medicalRecord2 = new MedicalRecord();
    private static Person person = new Person();
    private static Person person2 = new Person();

    public String address1 = "10 baker Street";
    public String medication = "aspirin 300mg";
    public String allergies = "sea shells";
    public String phoneNumber = "158697";
    public int stationNumber = 1;

    @BeforeEach
    void setUp() {
        fireStation = new FireStation();
        fireStation.setId(1L);
        fireStation.setAddress("10 baker Street");
        fireStation.setStation(2);

        fireStationList.add(fireStation);

        address.add(fireStation.getAddress());

        person = new Person(1, "FirstTest1", "LastTest1", "3 Test", "city", "12345", "1234", "TEST","02/20/90",32, 1);
        person2 = new Person(2, "FirstTest2", "LastTest2", "3 Test", "city", "12345", "12345", "TEST","05/06/95",27,1);

        personList.add(person);
        personList.add(person2);

        medicalRecord1 = new MedicalRecord(1,"FirstTest1","LastTest1", LocalDate.of(2012,12,12));
        medicalRecord2 = new MedicalRecord(2,"FirstTest2","LastTest2", LocalDate.of(2001,12,12));
    }

    /**
     * PeopleCoveredByFireStationDto Test
     * @Test getByStationSector
     */
    public void getByStationSectorTest(){

        PeopleCoveredByFireStationDto peopleCovered = new PeopleCoveredByFireStationDto();
        List<PersonInfoDto> personInfoList = new ArrayList<>();
        peopleCovered.setAdults(1);
        peopleCovered.setChildren(1);

        PersonInfoDto personInfoDto1 = new PersonInfoDto();
        PersonInfoDto personInfoDto2 = new PersonInfoDto();
        personInfoList.add(personInfoDto1);
        personInfoList.add(personInfoDto2);
        peopleCovered.setPersonInfoDtos(personInfoList);

        when(fireStationRepository.findByStationNumber(1)).thenReturn(fireStationList);
        when(personRepository.findByFireAddress(address1)).thenReturn(personList);
        when(personService.createPersonInfoToStationNumber(personList)).thenReturn(peopleCovered);

        PeopleCoveredByFireStationDto coverage = (PeopleCoveredByFireStationDto) fireStationRepository.findByStationNumber(1);
        assertThat(coverage.getAdults()).isEqualTo(1);
        assertThat(coverage.getChildren()).isEqualTo(1);
        assertThat(coverage.getPersonInfoDtos()).isEqualTo(personInfoList);
    }
    @Test
    public void getByPhoneSectorTest() {
        PhoneAlertDto result = new PhoneAlertDto();
        List<String> phoneList = new ArrayList<>();
        phoneList.add(person.getPhone());
        phoneList.add(person2.getPhone());
        result.PhoneList(phoneList);

        when(personRepository.findByPhoneSector(address1, phoneNumber)).thenReturn(fireStationList);
        when(fireStationRepository.findByStationSector(address1)).thenReturn(personList);
        PhoneAlertDto mock = fireStationService.getByPhoneSector(phoneNumber, address1);

        assertThat(mock.getPhoneList()).isEqualTo(phoneList);
    }
    @Test
    public void createFloodTest() {
        List<Map<String, List<FloodStationDto>>> entry = new ArrayList<>();
        List<Integer> stationNumberList = new ArrayList<>();
        stationNumberList.add(fireStation.getStation());

        FloodStationDto flood = new FloodStationDto();
        FloodStationDto flood2 = new FloodStationDto();
        List<FloodStationDto> floodList = new ArrayList<>();
        floodList.add(flood);
        floodList.add(flood2);

        Map<String, List<FloodStationDto>> mapLocal = new HashMap<>();
        mapLocal.put(fireStation.getAddress(), floodList);
        entry.add(mapLocal);


        when(fireStationRepository.findByStationNumber(2)).thenReturn(fireStationList);
        when(personRepository.findAllByAddress(address.get(0))).thenReturn(personList);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecord1.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(medicalRecord2.getBirthdate());
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(
                Optional.ofNullable(medicalRecord1));
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(
                Optional.ofNullable(medicalRecord2));

        List<Map<String, List<FloodStationDto>>> result = fireStationService.getByStationNumber(stationNumberList);


        assertThat(result.get(0).get(fireStation.getAddress()).get(0).getLastName()).isEqualTo(entry.get(0).get(fireStation.getAddress()).get(0).getLastName());
        assertThat(result.get(0).get(fireStation.getAddress()).get(0).getPhone()).isEqualTo(entry.get(0).get(fireStation.getAddress()).get(0).getPhone());

    }


}
