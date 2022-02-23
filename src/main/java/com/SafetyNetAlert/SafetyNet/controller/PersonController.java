package com.SafetyNetAlert.SafetyNet.controller;


import com.SafetyNetAlert.SafetyNet.dto.ChildAlertDto;
import com.SafetyNetAlert.SafetyNet.dto.CommunityEmailDto;
import com.SafetyNetAlert.SafetyNet.dto.PersonInfoDto;
import com.SafetyNetAlert.SafetyNet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/personInfo/{firstName}/{lastName}")//Ok
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public ResponseEntity <List<PersonInfoDto>>getByFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        logger.info("Calling method: PersonInfo/firstName = " + firstName + " /lastName = " + lastName);
        ResponseEntity<List<PersonInfoDto>> result = ResponseEntity.status(HttpStatus.OK).body(personService.getByFirstNameAndLastName(firstName, lastName));
        logger.info("HttpResponse = " + result.getStatusCode());
        return result;
    }

    @GetMapping("/communityEmail/{city}")//Ok
    public ResponseEntity<List<CommunityEmailDto>>getAllCommunityEmail(@PathVariable("city") String city, @PathVariable ("email") List<String> email){
        logger.info("Calling method: CommunityEmail/city = " + city + "/email = " + email);
        ResponseEntity<List<CommunityEmailDto>> mail = ResponseEntity.status(HttpStatus.OK).body(personService.getBycity(email));
        logger.info("HttpResponse: " + mail.getStatusCode());
        return mail;
    }

    @GetMapping("/childAlert/{address}")//Ok
    public ResponseEntity<List<ChildAlertDto>>getPersonAgedEighteenAndUnder(@PathVariable("Age") int age, @PathVariable("address") String address){
    logger.info("Calling method: ChildAlert/Age = " + age + "/address = " + address);
    ResponseEntity<List<ChildAlertDto>> alert = ResponseEntity.status(HttpStatus.OK).body(personService.getByAddress(address));
        logger.info("HttpResponse: " + alert.getStatusCode());
    return alert ;}
}


