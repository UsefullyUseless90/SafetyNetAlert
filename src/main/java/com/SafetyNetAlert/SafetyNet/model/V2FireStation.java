package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2FireStation {

    private String id;
    private List<V2Family> familyList;

    public V2FireStation(FireStation firestation) {
        this.id = firestation.getStation();
        this.familyList = new ArrayList<>();
        this.getFamilyList().add(new V2Family(firestation.getAddress()));
    }

    public void addNewFamily(String address) {
        // TODO vérifier que l'adresse n'est pas en double
        boolean found = false;
        for (V2Family v2 : getFamilyList()) {
            if (v2.getAddress().contains(address)) {
                found = true;
            }
        }
        if (found == false) {
            this.getFamilyList().add(new V2Family(address));
        }
    }
}
