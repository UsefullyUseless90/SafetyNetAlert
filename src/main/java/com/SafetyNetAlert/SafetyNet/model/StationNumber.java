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
public class StationNumber {

    private List<CoveragePerson> personList;
    private int adults;
    private int children;

    public StationNumber(V2FireStation v2FireStation) {

        this.personList = new ArrayList<>();
        this.adults = v2FireStation.getFamilyList().get(0).getAdults();
        this.children = v2FireStation.getFamilyList().get(0).getChildren();
    }
        public List<CoveragePerson> filteredPeople(V2FireStation v2FireStation){
            List<V2Person> coveredPeople = new ArrayList<>();
            List<V2Family> coveredFamilies = new ArrayList<>();
            coveredPeople = v2FireStation.getFamilyList().get(0).getPersonList();
            coveredFamilies = v2FireStation.getFamilyList();
            List<CoveragePerson> cp = new ArrayList<>();
            for (int i = 0; i < coveredPeople.size(); i++) {
                if (v2FireStation.getFamilyList().get(0).getAddress().equals(coveredFamilies.get(0).getAddress())) {
                    CoveragePerson coveragePerson = new CoveragePerson();
                    coveragePerson.setLastName(v2FireStation.getFamilyList().get(0).getPersonList().get(i).getLastName());
                    coveragePerson.setFirstName(v2FireStation.getFamilyList().get(0).getPersonList().get(i).getFirstName());
                    coveragePerson.setAddress(v2FireStation.getFamilyList().get(0).getAddress());
                    coveragePerson.setPhoneNumber(v2FireStation.getFamilyList().get(0).getPersonList().get(i).getPhone());
                    this.personList.add(coveragePerson);
                }
            }
            return this.personList;
    }
}

