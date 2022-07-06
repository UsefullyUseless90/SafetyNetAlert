package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChildAlert {

    private String firstName;
    private String lastName;
    private int age;
    private String address;

    public ChildAlert(V2Person v2Person, String address) {
        this.firstName = v2Person.getFirstName();
        this.lastName = v2Person.getLastName();
        this.age = v2Person.getAge();
        this.address = address;
    }

}



