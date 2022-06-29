package com.SafetyNetAlert.SafetyNet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FireAddress {

    private String stationID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String address;
    private List<String> medications;
    private List<String> allergies;

    public FireAddress(V2Person v2Person, V2Family v2Family) {

        this.address = v2Family.getAddress();
        this.firstName = v2Person.getFirstName();
        this.lastName = v2Person.getLastName();
        this.phoneNumber = v2Person.getPhone();
        this.age = v2Person.getAge();
        this.medications = v2Person.getMedications();
        this.allergies = v2Person.getAllergies();
        this.stationID=null;

    }
}
