package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.dto.*;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
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

@Service
public class FireStationServiceImpl extends FireStationService {

    /**
     * FireStationRepository.
     */
    @Autowired
    private FireStationRepository repository;
    /**
     * PersonServiceImpl.
     */
    @Autowired
    private PersonServiceImpl personServiceImpl;
    /**
     * PersonRepository.
     */
    @Autowired
    private PersonRepository personRepository;
    /**
     * MedicalRecordRepository.
     */
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public FireStationServiceImpl(FireStationRepository fireStationRepository) {
        super(personRepository, fireStationRepository);
    }


    /**
     * Create PeopleCoveredByFireStationDto.
     * Reacts to FireStation Station Number Endpoint (#1) complete the function in PersonServiceImpl as well.
     *
     * @param address person's address.
     * @param station
     * @return a list of person's address.
     */

    //The point of this method is to get people covered by a specific station when asked
    public PeopleCoveredByFireStationDto getByStationSector(final String address, long station) {
        List<FireStation> fireStations = repository.findByStationSector(address);
        List<String> addresses = new ArrayList<>();
        PeopleCoveredByFireStationDto result;

        //We need to add the station's addresses in a list to do so
        for (int i = 0; i < fireStations.size(); i++) {
            addresses.add(fireStations.get(i).getAddress());
        }

        //Then we need to get a person's list with the corresponding addresses
        List<Person> personList = personRepository.findAllByAddress(address);

        // This finally creates infos for people covered by the corresponding station
        result = personServiceImpl.createPersonInfoToStationNumber(personList);
        return result;
    }

    /**
     * Create Phone Alert.
     * Reacts to Phone Alert Endpoint (#3).
     *
     * @param phone   person's phone number.
     * @param address person's address.
     * @return list of phone number.
     */

    public PhoneAlertDto getByPhoneSector(final String phone, String address) {

        List<Person> personSectorList = personRepository.findByPhoneSector(phone, address);
        List<String> phoneList = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        PhoneAlertDto alert = new PhoneAlertDto();

        for (Person p : personSectorList) {
            addresses.add(p.getAddress());
        }
        List<FireStation> stationList = repository.findByStationSector(address);
        for (FireStation f : stationList) {
            addresses.add(f.getAddress());
        }
        Set<String> mySet = new HashSet<String>(phoneList);
        List<String> filteredPhoneList = new ArrayList<String>(mySet);
        alert.PhoneList(filteredPhoneList);
        return alert;
    }


    /**
     * Create FloodStation.
     * Reacts to FloodStation Endpoint (#5).
     *
     * @param stationNumber station number.
     * @return List of map and list of flood.
     */
    public List<Map<String, List<FloodStationDto>>> getByStationNumber(final List<Integer> stationNumber) {
        List<Map<String, List<FloodStationDto>>> flood = new ArrayList<>();
        Map<String, List<FloodStationDto>> flood2 = new HashMap<>();
        List<FireStation> fireStation = new ArrayList<>();

        for (long sn : stationNumber) {
            List<FireStation> fireStation2 = repository.findByStationNumber(sn);
            fireStation.addAll(fireStation2);
        }
        for (FireStation f : fireStation) {
            List<Person> personList = personRepository.findAllByAddress(f.getAddress());
            List<FloodStationDto> floodList = new ArrayList<>();

            for (Person p : personList) {
                FloodStationDto floodStation = new FloodStationDto();

                LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                LocalDate now = LocalDate.now();
                int age = Period.between(birthdate, now).getYears();

                floodStation.setAge(age);
                floodStation.setLastName(p.getFirstName());
                floodStation.setPhone(p.getPhone());

                Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                if (medicalRecord.isPresent()) {
                    floodStation.setAllergies(p.getMedicalRecord().getAllergies());
                    floodStation.setMedications(p.getMedicalRecord().getMedications());
                }

                floodList.add(floodStation);
                flood2.put(f.getAddress(), floodList);
            }
            flood.add(flood2);
            return flood;
        }
        return null;
    }


    /**
     * Create fireAddress.
     * Reacts to FireAddress Endpoint (#4).
     * @param address the address.
     * @return list of fire.
     */

    public List<FireAddressDto> getByFireAddress(final String address, int stationNumber) {
        List<Person> people = personRepository.findByFireAddress(address);
        List<FireAddressDto> fireList = new ArrayList<FireAddressDto>();

        for (Person person : people) {
            FireAddressDto fire = new FireAddressDto();

            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate now = LocalDate.now();
            int age = 0;
            if (birthdate != null) {
                age = Period.between(birthdate, now).getYears();
            }
            List<FireStation> fireStation = fireStationRepository.findByStationNumber(stationNumber);
            fire.setLastName(person.getLastName());
            fire.setPhone(person.getPhone());
            fire.setAge(age);
            fire.setStationNumber(stationNumber);

            Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            if (medicalRecord.isPresent()) {
                PersonInfoDto personInfoDto = new PersonInfoDto();
                personInfoDto.setAllergies(medicalRecord.get().getAllergies());
                personInfoDto.setMedications(medicalRecord.get().getMedications());
            } else {
                fire.setAllergies(new ArrayList<>());
                fire.setMedications(new ArrayList<>());
            }
            fireList.add(fire);
        }
        return fireList;
    }
}