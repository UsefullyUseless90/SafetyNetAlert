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

    /**
     * @param dataJson
     * @throws IOException
     */
    public V2FireStationList(DataJson dataJson) throws IOException {

        this.setStations(new ArrayList<>());
        // Extracting station's List
        for (FireStation f : dataJson.getFirestations()) {
            boolean found = false;// gestion doublons station (748 Townings Dr).
            boolean match = false;// gestion doublons station / adresse (112 Steppes Pl (St 3, St4)) .
            for (V2FireStation v2 : stations) { // Vérifie que dans la liste
                if (v2.getId().equals(f.getStation())) { // que la station ajoutée n'existe pas
                    v2.addNewFamily(f.getAddress());// si elle existe on lui ajoute la famille
                    found = true;// **là elle existe
                }
            }
            // il faut maintenant vérifier que la station qui va être
            // créée ne gère pas la même adresse qu'une autre station.
            if (found == false) {
                stations.add(new V2FireStation(f));// ** là non, on ajoute alors la station.
            }
        }


        for (Person p : dataJson.getPersons()) {
            for (int i2 = 0; i2 < this.stations.size(); i2++) {
                for (int n = 0; n < this.stations.get(i2).getFamilyList().size(); n++) {
                    boolean found = false;
                    if (this.getStations().get(i2).getFamilyList().get(n).getAddress().equals(p.getAddress())) {
                        this.getStations().get(i2).getFamilyList().get(n).addPerson(p);
                        found = true;
                        break;
                    }
                }
            }
        }
            //Initialiser les medicals records

        for (MedicalRecord medicalRecord : dataJson.getMedicalrecords()) {
                for (int i = 0; i < stations.size(); i++) {
                    for (int j = 0; j < stations.get(i).getFamilyList().size(); j++) {
                        for (int k = 0; k < this.stations.get(i).getFamilyList().get(j).getPersonList().size(); k++) {
                            if (this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getFirstName().equals(medicalRecord.getFirstName())
                                    && this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).getLastName().equals(medicalRecord.getLastName())) {
                                this.stations.get(i).getFamilyList().get(j).getPersonList().get(k).initMedicalRecords(medicalRecord);
                                break;

                            }
                        }
                    }
                }
            }

            for (int i = 0; i < this.stations.size(); i++) {
                for (int j = 0; j < stations.get(i).getFamilyList().size(); j++) {
                    this.stations.get(i).getFamilyList().get(j).calculateAge();
                }
            }
        }
    }
