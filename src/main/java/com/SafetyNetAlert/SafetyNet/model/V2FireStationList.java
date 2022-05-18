package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2FireStationList {

    private List<V2FireStation> stations;

    public V2FireStationList(DataJson dataJson) throws IOException {
        this.setStations(new ArrayList<>());
        for (FireStation f : dataJson.getFirestations()) {
            this.getStations().add(new V2FireStation(f));
        }
        for (Person p : dataJson.getPersons()) {
            for (int i2 = 0; i2 < this.stations.size(); i2++) {
                for (int n = 0; n < this.stations.get(i2).getFamilyList().size(); n++) {
                    boolean found = false;
                    if (this.getStations().get(i2).getFamilyList().get(n).getAddress().equals(p.getAddress())) {
                        this.getStations().get(i2).getFamilyList().get(n).addPerson(p);
                        found = true;
                        break;
                        // Problème station 3
                    }
                }
                //break;
            }
        }
        //Initialiser les medicals records
        int j = 0;
        //int k = 0;
        for (MedicalRecord medicalRecord : dataJson.getMedicalrecords()) {
            for (int i = 0; i < stations.size(); i++) {
                for (int k = 0; k < this.stations.get(i).getFamilyList().get(j).getPersonList().size(); k++) {
                    boolean found = false;
                    if (this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getFirstName().equals(medicalRecord.getFirstName())
                            && this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getLastName().equals(medicalRecord.getLastName())) {
                        this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).initMedicalRecords(medicalRecord);
                        found = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < this.stations.size(); i++) {
            this.stations.get(i).getFamilyList().get(j).calculateAge();
        }
    }
}