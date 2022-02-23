package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.dto.*;
import com.SafetyNetAlert.SafetyNet.model.MedicalRecord;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.repository.FireStationRepository;
import com.SafetyNetAlert.SafetyNet.repository.MedicalRecordRepository;
import com.SafetyNetAlert.SafetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * The type Person service.
 */
@Service
public class PersonServiceImpl extends PersonService {
    /**
     * PersonRepository.
     */
    @Autowired
    private PersonRepository repository;
    /**
     * MedicalRecordRepository.
     */
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    /**
     * FireStationRepository.
     */
    @Autowired
    private FireStationRepository fireStationRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        super(personRepository);
    }


    /**
     * Create PeopleCoveredByFireStationDto.
     * Reacts to FireStation Station Number Endpoint (#1) complete the function in FireStationServiceImpl as well.
     * @param personList the person list.
     * @return peopleCoveredByFireStation.
     */

    public PeopleCoveredByFireStationDto createPersonInfoToStationNumber(final List<Person> personList) {
        PeopleCoveredByFireStationDto peopleCovered = new PeopleCoveredByFireStationDto();
        List<PersonInfoDto> result = new ArrayList();

        for (Person person : personList) {
            PersonInfoDto personInfoDto = new PersonInfoDto();
            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate now = LocalDate.now();
            int age = 0;
            if (birthdate != null) {
                age = Period.between(birthdate, now).getYears();
            }
            peopleCovered.setFirstName(person.getFirstName());
            peopleCovered.setLastName(person.getLastName());
            peopleCovered.setPhone(person.getPhone());
            peopleCovered.setAddress(person.getAddress());
            result.add(personInfoDto);
            if (age < 18) {
                PeopleCoveredByFireStationDto.setChildren(PeopleCoveredByFireStationDto.getChildren() + 1);
            } else {
                PeopleCoveredByFireStationDto.setAdults(PeopleCoveredByFireStationDto.getAdults() + 1);
            }
        }
        peopleCovered.setPersonInfoDtos(result);
        return peopleCovered;
    }

    /**
     * Create childAlert.
     * Reacts to ChildAlert Endpoint (#2).
     * @param address the address.
     * @return ChildAlertDto.
     */

    public List<ChildAlertDto> getByAddress(String address) {
        List<Person> personList = new repository.findByAddress(address);
        List<ChildAlertDto> result = new ArrayList<>();
        List<Person> children = new ArrayList<>();
        List<Person> adults = new ArrayList<>();
        for (Person person : personList) {
            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate now = LocalDate.now();
            int age = 0;
            if (birthdate != null) {
                age = Period.between(birthdate, now).getYears();
            }
            if (age > 18) {
                adults.add(person);
            } else {
                Person child = new Person();

                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(person.getAge());
                children.add(child);
            }
        }
        result.setNumberOfChildren.add(children);// Attribute the count of children to a list
        result.setNumberOfAdults.add(adults);// Attribute the count of Adults to a list
        return result;
    }

    /**
     * Create communityEmail list.
     * Reacts to CommunityMail Endpoint (#7).
     * @param city
     * @return communityMail.
     */

    public List<CommunityEmailDto> getByCity(String city) {
        List<Person> personList = repository.findByCity(city);
        List<CommunityEmailDto> result = new ArrayList<>();
        List<String> email = new ArrayList<>();

        for (Person p : personList) {
            email.add(p.getEmail());

            Set<String> mySet = new HashSet<String>(email);
            List<String> filteredEmail = new ArrayList<>(mySet);
            result.setEmail(filteredEmail);
        }
            return result;
        }


/**
 * Create person info list.
 * Reacts to PersonInfo Endpoint (#6).
 * @param firstName the first name.
 * @param lastName  the last name.
 * @return the personInfo list.
 */

    public List<PersonInfoDto> createPersonInfoList(final String firstName, final String lastName) {
    List<PersonInfoDto> personResult = new ArrayList<>();
    List<Person> personList = repository.findByFirstNameAndLastName(firstName, lastName);

    for (Person p : personList){
        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setFirstName(p.getFirstName());
        personInfoDto.setLastName(p.getLastName());
        personInfoDto.setAddress(p.getAddress());
        personInfoDto.setEmail(p.getEmail());

        LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(p.getBirthDate(), p.getLastName());
        LocalDate now = LocalDate.now();
        int age = 0;
        if(birthdate != null){
            age = Period.between(now, birthdate).getYears();
        }
        personInfoDto.setAge(age);
        Optional<MedicalRecord>medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
        if(medicalRecord.isPresent()){
            personInfoDto.setMedications(medicalRecord.get().getMedications());
            personInfoDto.setAllergies(medicalRecord.get().getAllergies());
        }else{
            personInfoDto.setMedications(new ArrayList<>());
            personInfoDto.setAllergies(new ArrayList<>());
        }
        personResult.add(personInfoDto);
        }
    return personResult;
    }

}
