package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public
class CoveragePerson {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public CoveragePerson(V2Person v2Person, String address) {
            this.firstName = v2Person.getFirstName();
            this.lastName = v2Person.getLastName();
            this.phoneNumber = v2Person.getPhone();
            this.address = address;
    }
}