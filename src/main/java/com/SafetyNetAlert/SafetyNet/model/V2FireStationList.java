package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2FireStationList {

    private List<V2FireStation> stations;

    public V2FireStationList(DataJson dataJson) throws IOException {
        this.setStations(new ArrayList<>());
        for (FireStation f : dataJson.getFirestations()){
         this.getStations().add(new V2FireStation(f));
        }
        List<Person> people = new ArrayList<>();
        people = dataJson.getPersons();
        int house = 0;
        while(house < people.size()) {
            for (Person p : people) {
                for (int i = 0; i < stations.size(); i++) {
                    boolean found = false;
                    if (this.getStations().get(i).getFamilyList().get(house).getAddress().equals(p.getAddress())) {
                        this.getStations().get(i).getFamilyList().get(house).addPerson(p);
                        found = true;
                        break;

                    }
                }
            }
            break;
        }
        // MedicalRecords
        //public void initMedicalRecords(){
        }
        }


