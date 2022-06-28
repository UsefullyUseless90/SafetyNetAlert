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

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String address;
    private List<String> medications;
    private List<String> allergies;

    public FireAddress(V2Person v2, V2Family v2Family) {
        this.firstName = v2.getFirstName();
        this.lastName = v2.getLastName();
        this.phoneNumber = v2.getPhone();
        this.age = v2.getAge();
        this.address = v2Family.getAddress();
        this.medications = v2.getMedications();
        this.allergies = v2.getAllergies();



    }
}
