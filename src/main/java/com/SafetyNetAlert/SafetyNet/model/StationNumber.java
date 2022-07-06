package com.SafetyNetAlert.SafetyNet.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StationNumber {

    private List<CoveragePerson> personList;
    private int adults;
    private int children;

    public StationNumber(V2FireStation v2FireStation) {

        this.personList = new ArrayList<>();
        this.adults = 0;
        this.children = 0;

        for(V2Family v2Family : v2FireStation.getFamilyList()) {
            for(V2Person v2Person : v2Family.getPersonList()) {
                CoveragePerson coveragePerson = new CoveragePerson(v2Person, v2Family.getAddress());
                this.getPersonList().add(coveragePerson);
            }
            this.setAdults(v2Family.getAdults() + this.getAdults());
            this.setChildren(v2Family.getChildren() + this.getChildren());
        }
    }
}

