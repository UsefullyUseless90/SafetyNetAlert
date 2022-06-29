package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceUrlsImpl implements ServiceUrls {

    @Autowired
    JsonFileService jsonFileService;

    //URL n°2

    /**
     *
     * @param address
     * @return
     * @throws IOException
     */

    public ChildList childAlert(String address) throws IOException { // OK
        List<ChildAlert> childs = new ArrayList<>();
        CoveragePerson coveragePerson = new CoveragePerson();
        List<CoveragePerson> personList = new ArrayList<>();
        ChildList children = new ChildList();
        V2FamilyList v2FamilyList = new V2FamilyList(jsonFileService.jsonReaderService());
            for (V2Family v2Family : v2FamilyList.getFamilies()) {
                if (v2Family.getAddress().equals(address)) {
                    for (V2Person v2Person : v2Family.getPersonList()) {
                        ChildAlert childAlert = new ChildAlert();
                        if (v2Person.getAge()>18) {
                            coveragePerson = new CoveragePerson(v2Person, v2Family.getAddress());
                            personList.add(coveragePerson);
                        } else {
                            childAlert = new ChildAlert(v2Person, address);
                            childs.add(childAlert);
                            children.setChildList(childs);
                        }

                        children.setOtherMembers(personList);
                    }
                }
            }
        return children;
    }

    // URL n°3

    /**
     *
     * @param firestationNumber
     * @return
     * @throws IOException
     */

    public PhoneNumber phoneNumber(String firestationNumber) throws IOException { // OK
        PhoneNumber phoneNumber = new PhoneNumber();
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        for(V2FireStation v2FireStation : v2FireStationList.getStations()){
            if(v2FireStation.getId().equals(firestationNumber)){
                        phoneNumber = new PhoneNumber(v2FireStation);
            }
        }
        return phoneNumber;
    }

    // URL n°4

    /**
     *
     * @param address
     * @return
     */

    @Override
    public List<FireAddress> fireAddress(String address) throws IOException { // OK
        FireAddress fireAddress = new FireAddress();
        List<FireAddress> fireAddresses = new ArrayList<>();
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        for(V2FireStation v2FireStation : v2FireStationList.getStations())
        for (V2Family v2 : v2FireStation.getFamilyList()) {
            for (V2Person v2Person : v2.getPersonList()) {
                if (v2.getAddress().equals(address)) {
                    fireAddress = new FireAddress(v2Person, v2);
                    fireAddress.setStationID(v2FireStation.getId());
                    fireAddresses.add(fireAddress);
                }
            }
        }
        return fireAddresses;
    }

    // URL n°5

    /**
     *
     * @param aListOfStationNumber
     * @return
     * @throws IOException
     */

    public List<FireAddress> floodStation(List<String> aListOfStationNumber) throws IOException { // OK
        FireAddress fireAddress = new FireAddress();
        List<FireAddress> fireAddresses = new ArrayList<>();
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        for(V2FireStation v2FireStation : v2FireStationList.getStations()){
            for (V2Family v2 : v2FireStation.getFamilyList()) {
                for (V2Person v2Person : v2.getPersonList()) {
                    for(int i = 0; i<aListOfStationNumber.size(); i++) {
                        if (v2FireStation.getId().equals(aListOfStationNumber.get(i))) {
                            fireAddress = new FireAddress(v2Person, v2);
                            fireAddress.setStationID(v2FireStation.getId());
                            fireAddresses.add(fireAddress);
                        }
                    }
                }
            }
        }
        return fireAddresses;
    }

    // URL n°6

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     * @throws IOException
     */
    @Override
    public List<PersonInfo> personInfo(String firstName, String lastName) throws IOException { // OK
        PersonInfo personInfo = new PersonInfo();
        List<PersonInfo> personInfoList = new ArrayList<>();
        V2FamilyList v2Families = new V2FamilyList(jsonFileService.jsonReaderService());
        for(V2Family v2 : v2Families.getFamilies()){
            for(V2Person v2Person : v2.getPersonList()){
                    if (lastName.equals(v2Person.getLastName())) {
                        personInfo = new PersonInfo(v2Person, v2);
                        personInfoList.add(personInfo);
                    }
            }
        }
        return personInfoList;
    }

    //URL n°7

    /**
     *
     * @param city
     * @return
     * @throws IOException
     */

    public List<CommunityEmail> communityEmail(String city) throws IOException { // OK
        List<CommunityEmail>communityMailList = new ArrayList<>();
        CommunityEmail communityEmail = new CommunityEmail();
        List<Person> personList = jsonFileService.jsonReaderService().getPersons();
        for (Person p : personList) {
                if(city.equals(p.getCity())){
                    communityEmail = new CommunityEmail(p);
                    communityMailList.add(communityEmail);
                }
            }
        return communityMailList;
    }

}


