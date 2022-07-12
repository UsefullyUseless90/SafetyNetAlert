package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.*;
import com.SafetyNetAlert.SafetyNet.service.ServiceUrls;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class UrlsController {

    private static final Logger log = LogManager.getLogger(UrlsController.class);

    @Autowired
    private ServiceUrls serviceUrls;

    /**
     *
     * @param address
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/childAlert", params = "address", method = RequestMethod.GET)
    public ResponseEntity<?> childAlert(@RequestParam String address) throws IOException{
        log.info("Checking, please wait...");
        log.info("URL n°2 ChildAlert:" + serviceUrls.childAlert(address));
        return ResponseEntity.status(HttpStatus.OK).body(serviceUrls.childAlert(address));
    }

    /**
     *
     * @param firestation
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/phoneAlert", params = "firestation")
    public ResponseEntity<?>phoneNumber(@RequestParam String firestation) throws IOException{
        log.info("Checking, please wait...");
        PhoneNumber phoneNumber = serviceUrls.phoneNumber(firestation);
        phoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber().stream().distinct().collect(Collectors.toList()));
        log.info("URL n°3 PhoneAlert:" + phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(phoneNumber);
    }

    /**
     *
     * @param address
     * @return
     * @throws IOException
     */

    @RequestMapping(value ="/fire", params = "address", method = RequestMethod.GET)
    public ResponseEntity<?>fireAddress(@RequestParam String address) throws IOException {
        log.info("Checking, please wait...");
        List<FireAddress> fireAddress = serviceUrls.fireAddress(address);
        log.info("URL n°4 FireAddress:" + fireAddress);
        return ResponseEntity.status(HttpStatus.OK).body(fireAddress);
    }

    /**
     *
     * @param stations
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/flood/stations", params = "stations")
    public ResponseEntity<?>floodStation(@RequestParam("stations") List<String> stations) throws IOException {
        log.info("Checking, please wait...");
        List<FireAddress> floodStation = serviceUrls.floodStation(stations);
        log.info("URL n°5 FloodStation:" + serviceUrls.floodStation(stations));
        return ResponseEntity.status(HttpStatus.OK).body(serviceUrls.floodStation(stations));
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/personInfo", params ={ "firstName", "lastName"})
    public ResponseEntity<?> personInfo(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
        log.info("Checking, please wait...");
        List<PersonInfo> personInfo = serviceUrls.personInfo(firstName, lastName);
        log.info("URL n°6 PersonInfo:" + personInfo);
        return ResponseEntity.status(HttpStatus.OK).body(personInfo);
    }

    /**
     *
     * @param city
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/communityEmail", params = "city")
    public ResponseEntity<?>communityEmail(@RequestParam String city) throws IOException {
        log.info("Checking, please wait...");
        List<CommunityEmail> communityEmails = serviceUrls.communityEmail(city);
        log.info("URL n°7 CommunityEmail:" + communityEmails);
        return ResponseEntity.status(HttpStatus.OK).body(communityEmails);
    }

}
