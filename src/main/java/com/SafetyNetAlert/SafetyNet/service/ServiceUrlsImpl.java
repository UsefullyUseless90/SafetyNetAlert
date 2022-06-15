package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUrlsImpl {

    JsonFileService jsonFileService;

    // TODO = retourner une liste d'enfants(-18 ans), nom, prénom, age.
    // TODO = retourner liste autres membres foyer, si 0 enfant = void.
    List<?> childAlert() { // URL n°2

        return null;
    }

    // TODO = retourner liste de n° de téléphone des résidents desservis par la caserne de pompier.

    List<?> phoneAlert() {  // URL n°3
        return null;
    }

    // TODO = retourner liste des habitants à l'adresse + n° caserne pompiers concernée par l'adresse.
    // TODO = retourner liste (nom, n° de téléphone, age et antécédents médicaux(traitements + allergies)).

    List<?> fireAddress() { // URL n°4

        return null;
    }

    //TODO = retourner une liste de tous les foyers desservis par la station de pompiers.
    // TODO = Liste doit regrouper les personnes par adresse, doit aussi contenir (nom, prénom, phone, age & medicalRecords).

    List<?> floodStation() { // URL n°5
        return null;
    }

    //TODO = nom, adresse, age, email, & medicalRecords (avec chaque nom "à côté").

    List<?> personInfo() { // URL n°6
        return null;
    }

    // TODO = email de l'ensemble des personnes.

    List<Person> communityMail() throws IOException { // URL n°7

        List<Person> personList = jsonFileService.jsonReaderService().getPersons();
        List<Person> eMailList = new ArrayList<>();
        for(Person p : personList) {
            for (int i = 0; i < personList.size(); i++) {
                p.getCity();
                if (p.getCity().equals(personList.get(i).getCity()))
                    p.getEmail();
                eMailList.add(p);
            }
        }
        return eMailList;
    }
}
