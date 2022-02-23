package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.dto.*;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @param firstName people's firstName
     * @param lastName  people's lastName
     * @return list of people's info
     */

    public List<PersonInfoDto> getByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    /**
     * @return list of people's email from a city
     * @name getByEmail
     */

    public List<CommunityEmailDto> getByCity(String city) {
        return personRepository.findByCity(city).stream().map(this::convertEntityToCommunityEmailDto).collect(Collectors.toList());
    }

    /**
     * @param address people's address
     * @return list of people's age living at a specific address
     */

    public List<ChildAlertDto> getByAddress(String address) {
        return personRepository.findByAddress(address).stream().map(this::convertEntityToChildAlertDto).collect(Collectors.toList());
    }

    /**
     * fireAddress
     *
     * @param address people's address
     * @return list of people's age living by
     */
    public List<FireAddressDto> getByFireAddress(String address) {
        return personRepository.findByFireAddress(address).stream().map(this::convertEntityToFireAddressDto).collect(Collectors.toList());
    }


    private PersonInfoDto convertEntityToDto(Person person) { // getByFirstAndLastName

        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setFirstName(person.getFirstName());
        personInfoDto.setLastName(person.getLastName());
        personInfoDto.setAddress(person.getAddress());
        personInfoDto.setAge(person.getAge());
        personInfoDto.setEmail(person.getEmail());
        personInfoDto.setMedications(person.getMedicalRecord().getMedications());
        personInfoDto.setAllergies(person.getMedicalRecord().getAllergies());

        return personInfoDto;/**Ok**/

    }

    private ChildAlertDto convertEntityToChildAlertDto(Person person) {    //getByAddress

        ChildAlertDto convertEntityToChildAlertDto = new ChildAlertDto();
        convertEntityToChildAlertDto.setNumberOfChildren(person.getAlertChild());
        convertEntityToChildAlertDto.setNumberOfAdults(person.getAlertAdults());
        convertEntityToChildAlertDto.setFirstName(person.getFirstName());
        convertEntityToChildAlertDto.setLastName(person.getLastName());
        convertEntityToChildAlertDto.setAge(person.getAge());

        return convertEntityToChildAlertDto;
    }

    private FireAddressDto convertEntityToFireAddressDto(Person person) {   //getByFireAddress
        FireAddressDto fireAddressDto = new FireAddressDto();
        fireAddressDto.setStationNumber(person.getStationNumber());
        fireAddressDto.setLastName(person.getLastName());
        fireAddressDto.setPhone(person.getPhone());
        fireAddressDto.setAge(person.getAge());
        fireAddressDto.setMedications(person.getMedicalRecord().getMedications());
        fireAddressDto.setAllergies(person.getMedicalRecord().getAllergies());

        return fireAddressDto;/**Ok**/
    }

    private CommunityEmailDto convertEntityToCommunityEmailDto(Person person) {

        CommunityEmailDto convertEntityToCommunityEmailDto = new CommunityEmailDto();
        convertEntityToCommunityEmailDto.setEmail(person.getEmail());

        return convertEntityToCommunityEmailDto;
    }

}





