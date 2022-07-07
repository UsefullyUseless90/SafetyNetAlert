package com.SafetyNetAlert.SafetyNet.controller;

import com.SafetyNetAlert.SafetyNet.model.CommunityEmail;
import com.SafetyNetAlert.SafetyNet.model.PhoneNumber;
import com.SafetyNetAlert.SafetyNet.service.ServiceUrls;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UrlsController {

    @Autowired
    ServiceUrls serviceUrls;

    /**
     *
     * @param address
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/childAlert", params = "address", method = RequestMethod.GET)
    public ResponseEntity<?> childAlert(@RequestParam String address) throws IOException{
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
        PhoneNumber phoneNumber = serviceUrls.phoneNumber(firestation);
        phoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber().stream().distinct().collect(Collectors.toList()));
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
        return ResponseEntity.status(HttpStatus.OK).body(serviceUrls.fireAddress(address));
    }

    /**
     *
     * @param stations
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/flood/stations", params = "stations")
    public ResponseEntity<?>floodStation(@RequestParam("stations") List<String> stations) throws IOException {
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
        return ResponseEntity.status(HttpStatus.OK).body(serviceUrls.personInfo(firstName, lastName));
    }

    /**
     *
     * @param city
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/communityEmail", params = "city")
    public ResponseEntity<?>communityEmail(@RequestParam String city) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(serviceUrls.communityEmail(city));
    }

}
