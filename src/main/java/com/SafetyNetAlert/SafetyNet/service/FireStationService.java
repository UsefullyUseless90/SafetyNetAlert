package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.dto.PeopleCoveredByFireStationDto;
import com.SafetyNetAlert.SafetyNet.dto.FloodStationDto;
import com.SafetyNetAlert.SafetyNet.dto.PhoneAlertDto;
import com.SafetyNetAlert.SafetyNet.model.FireStation;
import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.repository.FireStationRepository;
import com.SafetyNetAlert.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    public final PersonRepository personRepository;

    public final FireStationRepository fireStationRepository;

    public FireStationService(PersonRepository personRepository, FireStationRepository fireStationRepository){
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
    }

    public List<FloodStationDto>getByStationNumber(long station) {
    return fireStationRepository.findByStationNumber(station).stream().map(this::convertEntityToFloodStationDto).collect(Collectors.toList());
    }
    public List<PeopleCoveredByFireStationDto>getByStationSector(String address){
        return fireStationRepository.findByStationSector(address).stream().map(this :: convertEntityToFireStationCoverageDto).collect(Collectors.toList());
    }
    public List<PhoneAlertDto>getByStationPhoneSector(String address, String phone){
        return personRepository.findByPhoneSector(address, phone).stream().map(this::convertEntityToPhoneAlertDto).collect(Collectors.toList());
    }



    private PhoneAlertDto convertEntityToPhoneAlertDto(Person person) { // ????

        PhoneAlertDto convertEntityToPhoneAlertDto = new PhoneAlertDto();
        convertEntityToPhoneAlertDto.setPhone(person.getPhone());

        return convertEntityToPhoneAlertDto;

    }

    private FloodStationDto convertEntityToFloodStationDto(FireStation fireStation){   // getStationNumber //OK

        FloodStationDto convertEntityToFloodStationDto = new FloodStationDto();
        convertEntityToFloodStationDto.setLastName(fireStation.getPerson().getLastName());
        convertEntityToFloodStationDto.setPhone(fireStation.getPerson().getPhone());
        convertEntityToFloodStationDto.setAge(fireStation.getPerson().getAge());
        convertEntityToFloodStationDto.setAllergies(fireStation.getPerson().getMedicalRecord().getAllergies());
        convertEntityToFloodStationDto.setMedications(fireStation.getPerson().getMedicalRecord().getMedications());

        return convertEntityToFloodStationDto;
    }

    private PeopleCoveredByFireStationDto convertEntityToFireStationCoverageDto(FireStation fireStation){   //getByStationSector
        PeopleCoveredByFireStationDto peopleCoveredByFireStationDto = new PeopleCoveredByFireStationDto();
        peopleCoveredByFireStationDto.setAddress(fireStation.getPerson().getAddress());
        peopleCoveredByFireStationDto.setFirstName(fireStation.getPerson().getFirstName());
        peopleCoveredByFireStationDto.setLastName(fireStation.getPerson().getLastName());
        peopleCoveredByFireStationDto.setPhone(fireStation.getPerson().getPhone());
        peopleCoveredByFireStationDto.setChildren(fireStation.getPerson().getChildren());
        peopleCoveredByFireStationDto.setAdults(fireStation.getPerson().getAdults());

        return peopleCoveredByFireStationDto;/**Ok**/

    }



}
