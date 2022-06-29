package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class V2FamilyList {

    private List<V2Family> families;

    public V2FamilyList(DataJson dataJson) throws IOException {
        this.families = new ArrayList<>();


        for (Person p : dataJson.getPersons()) {
            boolean found = false;
            for(V2Family v2Family : families){
                if(p.getAddress().equals(v2Family.getAddress())){
                    v2Family.addPerson(p);
                    found = true;
                }
            }
            if(found == false){
                V2Family v2Family = new V2Family(p.getAddress());
                v2Family.addPerson(p);
                families.add(v2Family);
            }
        }

        for (MedicalRecord medicalRecord : dataJson.getMedicalrecords()) {
            for (int i = 0; i < families.size(); i++) {
                for (int j = 0; j < families.get(i).getPersonList().size(); j++) {
                        if (this.families.get(i).getPersonList().get(j).getFirstName().equals(medicalRecord.getFirstName())
                                && this.families.get(i).getPersonList().get(j).getLastName().equals(medicalRecord.getLastName())) {
                            this.families.get(i).getPersonList().get(j).initMedicalRecords(medicalRecord);
                            break;
                        }
                }
            }
        }
    }
}
