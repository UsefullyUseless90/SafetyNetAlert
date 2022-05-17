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
            for (int i = 0; i < stations.size(); i++) {
                for (int j = 0; j < this.getStations().get(i).getFamilyList().size(); j++) {
                    boolean found = false;
                    if (this.getStations().get(i).getFamilyList().get(j).getAddress().equals(p.getAddress())) {
                        this.getStations().get(i).getFamilyList().get(j).addPerson(p);
                        found = true;
                        break;
                        // Problème station 3
                    }
                }
            }
        }
        //Initialiser les medicals records
        for (MedicalRecord medicalRecord : dataJson.getMedicalrecords()) {
            for (int i = 0; i < stations.size(); i++) {
                for (int j = 0; j < getStations().get(i).getFamilyList().size(); j++) {
                    for (int k = 0; k < getStations().get(i).getFamilyList().get(j).getPersonList().size(); k++) {
                        boolean found = false;
                        if (this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getFirstName().equals(medicalRecord.getFirstName())
                                && this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getLastName().equals(medicalRecord.getLastName())) {
                            this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).initMedicalRecords(medicalRecord);
                            found = true;
                            break;
                        }
                            // bouclage personnes (garder les couches en tête et refléchir aux couches!!)
                            for (V2Person p2 : this.stations.get(i).getFamilyList().get(j).getPersonList()) {
                                LocalDate birthDate = p2.getBirthdate();
                                LocalDate now = LocalDate.now();
                                int difference = Period.between(birthDate, now).getYears();
                                for (int l = 0; l < this.stations.get(i).getFamilyList().get(j).getPersonList().size(); l++) {
                                    if (difference >= 18) {
                                        this.stations.get(i).getFamilyList().get(j).setAdults(+1);
                                    } else {
                                        this.stations.get(i).getFamilyList().get(j).setChildren(+1);
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

