package com.SafetyNetAlert.SafetyNet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PhoneNumber {

    private List<String> phoneNumber;

    public PhoneNumber(V2FireStation v2FireStation) {

        this.phoneNumber = new ArrayList<>();

        for(V2Family v2Family : v2FireStation.getFamilyList()){
            for(V2Person v2Person : v2Family.getPersonList()) {
                for (int i = 0; i < v2FireStation.getFamilyList().size(); i++) {
                    if (v2Family.getAddress().equals(v2FireStation.getFamilyList().get(i).getAddress())){
                        this.phoneNumber.add(v2Person.getPhone());
                        break;
                    }
                }
            }
        }
    }
}

