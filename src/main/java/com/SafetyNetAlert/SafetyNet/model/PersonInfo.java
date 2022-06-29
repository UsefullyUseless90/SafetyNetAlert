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
public class PersonInfo {

    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public PersonInfo(V2Person v2Person, V2Family v2Family) {

        this.firstName = v2Person.getFirstName();
        this.lastName = v2Person.getLastName();
        this.age = v2Person.getAge();
        this.address = v2Family.getAddress();
        this.email = v2Person.getEmail();
        this.medications = v2Person.getMedications();
        this.allergies = v2Person.getAllergies();

    }

}
