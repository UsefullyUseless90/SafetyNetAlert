package com.SafetyNetAlert.SafetyNet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class FireAddress {

    private String stationID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public FireAddress(V2Person v2Person) {

        this.firstName = v2Person.getFirstName();
        this.lastName = v2Person.getLastName();
        this.phoneNumber = v2Person.getPhone();
        this.age = v2Person.getAge();
        this.medications = v2Person.getMedications();
        this.allergies = v2Person.getAllergies();
        this.stationID=null;

    }
}
