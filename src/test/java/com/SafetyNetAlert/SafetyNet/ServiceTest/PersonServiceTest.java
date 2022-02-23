package com.SafetyNetAlert.SafetyNet.ServiceTest;

import com.SafetyNetAlert.SafetyNet.dto.Child;
import com.SafetyNetAlert.SafetyNet.dto.ChildAlertDto;
import com.SafetyNetAlert.SafetyNet.dto.CommunityEmailDto;
import com.SafetyNetAlert.SafetyNet.dto.PersonInfoDto;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.repository.FireStationRepository;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import com.SafetyNetAlert.SafetyNet.repository.PersonRepository;
import com.SafetyNetAlert.SafetyNet.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;


public class PersonServiceTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private PersonServiceImpl personService;

    private static final Person person = new Person();
    private static Person person2 = new Person();
    private static Person person3 = new Person();
    private static MedicalRecord medicalRecords1 = new MedicalRecord();
    private static MedicalRecord medicalRecords2 = new MedicalRecord();
    private static MedicalRecord medicalRecords3 = new MedicalRecord();
    private static final List<Person> personList = new ArrayList<>();
    private static final List<Person> personListFire = new ArrayList<>();
    private static final FireStation fireStation = new FireStation();

    /**
     * Test ChildAlert
     * @test getByAddress
     */
    @Test
    public void getByAddressTest(){
        ChildAlertDto alert = new ChildAlertDto();
        List<Person> children = new ArrayList<>();
        Person child = new Person();
        children.add(child);

        Person person = new Person();
        List<Person> localPeople = new ArrayList<>();
        localPeople.add(person);

        alert.setNumberOfChildren(children);
        alert.setNumberOfAdults(localPeople);

        when(personRepository.findByAddress("10 Baker Street")).thenReturn(personList);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecords1.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(medicalRecords2.getBirthdate());

        List<ChildAlertDto> mock = personService.getByAddress("10 Baker Street");
        assertThat(mock.getFamily().get(0).getFirstName()).isEqualTo(alert.getFamily().get(0).getFirstName());
        assertThat(mock.getFamily().get(0).getLastName()).isEqualTo(alert.getFamily().get(0).getLastName());
        assertThat(mock.getFamily().get(0).getAddress()).isEqualTo(alert.getFamily().get(0).getAddress());
        assertThat(mock.getFamily().get(0).getEmail()).isEqualTo(alert.getFamily().get(0).getEmail());
        assertThat(mock.getFamily().get(0).getPhone()).isEqualTo(alert.getFamily().get(0).getPhone());

        assertThat(mock.getChildren().get(0).getFirstName()).isEqualTo(alert.getChildren().get(0).getFirstName());
        assertThat(mock.getChildren().get(0).getLastName()).isEqualTo(alert.getChildren().get(0).getLastName());
        assertThat(mock.getChildren().get(0).getAge()).isEqualTo(alert.getChildren().get(0).getAge());

    }

    /**
     * CommunityEmail Test
     * @Test getByCity
     */
    @Test
    public void getByCityTest(){

        CommunityEmailDto community = new CommunityEmailDto();
        List<String> email = new ArrayList<>();
        email.add("Mr.Test");
        email.add("mrTest@test.com");

        when(personRepository.findByCity("TestCity")).thenReturn(personList);

        List<CommunityEmailDto> mock = personService.getByCity("TestCity");
        assertThat(mock.getEmails()).isEqualTo(community.getEmails());

    }

    /**
     * PersonInfoDto Test
     * @Test createPersonInfoList
     */
    @Test
    public void createPersonInfoListTest(){

        List<PersonInfoDto> personInfoList = new ArrayList<>();
        PersonInfoDto personInfoList1 = new PersonInfoDto("Andrew", "Thompson", "10 baker Street", 25, "TEST", medicalRecords2.getMedications(),medicalRecords2.getAllergies());
        PersonInfoDto personInfoList2 = new PersonInfoDto("Leah", "Thompson", "10 baker Street", 23, "TEST54", medicalRecords3.getMedications(),medicalRecords3.getAllergies());
        personInfoList.add(personInfoList1);
        personInfoList.add(personInfoList2);

        when(personRepository.findByFirstNameAndLastName(person3.getLastName(), person3.getFirstName())).thenReturn(personListFire);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getLastName(), person2.getFirstName())).thenReturn(medicalRecords2.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person3.getLastName(), person3.getFirstName())).thenReturn(medicalRecords3.getBirthdate());
        when(medicalRecordRepository.findByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(Optional.ofNullable(medicalRecords2));
        when(medicalRecordRepository.findByFirstNameAndLastName(person3.getFirstName(), person3.getLastName())).thenReturn(Optional.ofNullable(medicalRecords3));

        List<PersonInfoDto> mock = personService.createPersonInfoList(person2.getFirstName(), person2.getLastName());

        assertThat(mock.get(0).getLastName()).isEqualTo(personInfoList.get(0).getLastName());
        assertThat(mock.get(0).getLastName()).isEqualTo(personInfoList.get(1).getLastName());
        assertThat(mock.get(0).getAllergies()).isEqualTo(personInfoList.get(0).getAllergies());
        assertThat(mock.get(1).getAllergies()).isEqualTo(personInfoList.get(1).getAllergies());
        assertThat(mock.get(0).getFirstName()).isEqualTo(personInfoList.get(0).getFirstName());
        assertThat(mock.get(1).getFirstName()).isEqualTo(personInfoList.get(1).getFirstName());
        assertThat(mock.get(0).getAge()).isEqualTo(personInfoList.get(0).getAge());
        assertThat(mock.get(1).getAge()).isEqualTo(personInfoList.get(1).getAge());
        assertThat(mock.get(0).getMedications()).isEqualTo(personInfoList.get(0).getMedications());
        assertThat(mock.get(1).getMedications()).isEqualTo(personInfoList.get(1).getMedications());

    }
    /**
     * PeopleCoveredByFireStationDto Test
     * @Test getByStationSector
     */
    public void createPersonInfoToStationNumberTest(){



    }

}
