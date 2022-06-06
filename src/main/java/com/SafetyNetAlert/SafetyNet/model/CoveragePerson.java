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

    public CoveragePerson(V2Family v2) {
        for (int i = 0; i < v2.getPersonList().size(); i++) {
            this.firstName = v2.getPersonList().get(i).getFirstName();
            this.lastName = v2.getPersonList().get(i).getLastName();
            this.address = v2.getAddress();
            this.phoneNumber = v2.getPersonList().get(i).getPhone();
        }

    }
}