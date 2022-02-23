package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.dto.FireAddressDto;
import com.SafetyNetAlert.SafetyNet.dto.PeopleCoveredByFireStationDto;
import com.SafetyNetAlert.SafetyNet.dto.FloodStationDto;
import com.SafetyNetAlert.SafetyNet.dto.PhoneAlertDto;
import com.SafetyNetAlert.SafetyNet.service.FireStationService;
import com.SafetyNetAlert.SafetyNet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class StationController {

    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private PersonService personService;

    Logger logger = LoggerFactory.getLogger(StationController.class);

    @GetMapping("/flood/{stations}")//Ok
    public ResponseEntity<List<FloodStationDto>> getByStationNumber(@PathVariable("station") long station, @PathVariable("address") String address, @PathVariable("id") long id) {
        logger.info("Calling Method: flood/Station = " + station + "/address = " + address + "/id = " + id);
        ResponseEntity<List<FloodStationDto>> flood = ResponseEntity.status(HttpStatus.OK).body(fireStationService.getByStationNumber(station));
        logger.info("HttpResponse: " + flood.getStatusCode());
        return flood;
    }

    @GetMapping("/fireStation/{stationNumber}") // Ok
    public ResponseEntity<List<PeopleCoveredByFireStationDto>> getByStationSector(@PathVariable("station") long station, @PathVariable("address") String address) {
        logger.info("Calling method: fireStation/station = " + station + "/address = " + address);
        ResponseEntity<List<PeopleCoveredByFireStationDto>> coverage = ResponseEntity.status(HttpStatus.OK).body(fireStationService.getByStationSector(address));
        logger.info("Http Response: " + coverage.getStatusCode());
        return coverage;
    }

    @GetMapping("/phoneAlert/{fireStation}")//Ok
    public ResponseEntity<List<PhoneAlertDto>> getByStationPhoneSector(String phone, String address) {
        logger.info("Calling method: phoneAlert/fireStation = " + phone + "/address = " + address);
        ResponseEntity<List<PhoneAlertDto>> phoneAlert = ResponseEntity.status(HttpStatus.OK).body(fireStationService.getByStationPhoneSector(phone, address));
        logger.info("Http Response:" + phoneAlert.getStatusCode());
        return phoneAlert;
    }


    @GetMapping("/fire/{address}")//Ok
    public ResponseEntity<List<FireAddressDto>> getByFireAddress(String address, int age) {
        logger.info("Calling Method: /FireAddress = " + address + age);
        ResponseEntity<List<FireAddressDto>> fire = ResponseEntity.status(HttpStatus.OK).body(personService.getByFireAddress(address));
        logger.info("Http Response: " + fire.getStatusCode());
        return fire;

    }
}