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

    // TODO = retourner une liste d'enfants(-18 ans), nom, prénom, age.
    // TODO = retourner liste autres membres foyer, si 0 enfant = void.
    // TODO = revoir les paramètres informations "other members" (possiblement pas besoin autant détails)

    /**
     *
     * @param address
     * @return
     * @throws IOException
     */

    public List<ChildAlert> childAlert(String address) throws IOException { // URL n°2
        List<ChildAlert> childs = new ArrayList<>();
        CoveragePerson coveragePerson = new CoveragePerson();
        List<CoveragePerson> personList = new ArrayList<>();
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
                        }

                        childAlert.setOtherMembers(personList);
                    }
                }
            }
        return childs;
    }

    // TODO = retourner liste de n° de téléphone des résidents desservis par la caserne de pompier.
    // URL n°3

    /**
     *
     * @param firestationNumber
     * @return
     * @throws IOException
     */

    public PhoneNumber phoneNumber(String firestationNumber) throws IOException {
        PhoneNumber phoneNumber = new PhoneNumber();
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        for(V2FireStation v2FireStation : v2FireStationList.getStations()){
            if(v2FireStation.getId().equals(firestationNumber)){
                        phoneNumber = new PhoneNumber(v2FireStation);
            }
        }
        return phoneNumber;
    }

    // TODO = retourner liste des habitants à l'adresse + n° caserne pompiers concernée par l'adresse.
    // TODO = retourner liste (nom, n° de téléphone, age et antécédents médicaux(traitements + allergies)).
    // URL n°4

    /**
     *
     * @param address
     * @return
     */

    @Override
    public List<FireAddress> fireAddress(String address) throws IOException {
        FireAddress fireAddress = new FireAddress();
        List<FireAddress> fireAddresses = new ArrayList<>();
        V2FamilyList v2FamilyList = new V2FamilyList(jsonFileService.jsonReaderService());
        for (V2Family v2Family : v2FamilyList.getFamilies()) {
            if (v2Family.getAddress().equals(address)) {
                for(V2Person v2 : v2Family.getPersonList()) {
                    fireAddress = new FireAddress(v2, v2Family);
                    fireAddresses.add(fireAddress);
                }
            }
        }
        return fireAddresses;
    }

    //TODO = retourner une liste de tous les foyers desservis par la station de pompiers.
    // TODO = Liste doit regrouper les personnes par adresse, doit aussi contenir (nom, prénom, phone, age & medicalRecords).
    // URL n°5

    /**
     *
     * @param aListOfStationNumber
     * @return
     * @throws IOException
     */

    @Override
    public FloodStation floodStation(String aListOfStationNumber) throws IOException {
        FloodStation floodStation = new FloodStation();
        FireAddress fireAddress = new FireAddress();
        List<FireAddress> fireAddresses = new ArrayList<>();
        V2FireStationList v2FireStationList = new V2FireStationList(jsonFileService.jsonReaderService());
        for(V2FireStation v2FireStation : v2FireStationList.getStations()){
            for(V2Family v2Family : v2FireStation.getFamilyList()){
                for(V2Person v2 : v2Family.getPersonList()) {
                    if (v2FireStation.getId().equals(floodStation.getStationID())) {
                        fireAddress = new FireAddress(v2, v2Family);
                        fireAddresses.add(fireAddress);
                    }
                }
            }
        }
        floodStation.setStationID(aListOfStationNumber);
        floodStation.setFireAddressList(fireAddresses);
        return floodStation;
    }

    //TODO = nom, adresse, age, email, & medicalRecords (avec chaque nom "à côté").
    // URL n°6

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     * @throws IOException
     */
    @Override
    public PersonInfo personInfo(String firstName, String lastName) throws IOException {

        PersonInfo personInfo = new PersonInfo();
        V2FamilyList v2Families = new V2FamilyList(jsonFileService.jsonReaderService());
        for(V2Family v2 : v2Families.getFamilies()){
            for(V2Person v2Person : v2.getPersonList()){
                if(firstName.equals(v2Person.getFirstName())&&(lastName.equals(v2Person.getLastName()))){
                    personInfo = new PersonInfo(v2Person, v2);
                }
            }
        }
        return personInfo;
    }
    // TODO = email de l'ensemble des personnes.

    /**
     *
     * @param city
     * @return
     * @throws IOException
     */

    public List<CommunityEmail> communityEmail(String city) throws IOException {
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


