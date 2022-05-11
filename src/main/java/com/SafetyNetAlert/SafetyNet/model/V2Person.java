package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2Person extends Person {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    /**
     *
     * @param p
     */
    public V2Person(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.birthdate = null;
        this.phone = p.getPhone();
        this.email = p.getEmail();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();

    }

    /**
     * @param dataJson
     */
    public void initMedicalRecords(DataJson dataJson) {
        for (MedicalRecord mR : dataJson.getMedicalrecords()) {
            getMedications().add(String.valueOf(mR));
        }
        for (Person p : dataJson.getPersons()) {
            for (int i = 0; i < medications.size(); i++) {
                boolean found = false;
                if (this.getMedications().get(i).equals(p.getLastName())) {
                } else if (this.getMedications().get(i).equals(p.getFirstName())) ;
                this.getMedications().add(String.valueOf(p));
                found = true;
                break;
            }
        }
        for (MedicalRecord mR : dataJson.getMedicalrecords()) {
            getAllergies().add(String.valueOf(mR));
        }
        for (Person p : dataJson.getPersons()) {
            for (int i = 0; i < allergies.size(); i++) {
                boolean found = false;
                if (this.getAllergies().get(i).equals(p.getLastName())) {
                } else if (this.getAllergies().get(i).equals(p.getFirstName())) ;
                this.getAllergies().add(String.valueOf(p));
                found = true;
                break;
            }
            this.setBirthdate(getBirthdate());
            for (MedicalRecord mR : dataJson.getMedicalrecords()) {
                boolean found = false;
                if (getFirstName().equals(p.getFirstName())) {
                } else if (getLastName().equals(p.getLastName())) ;
                this.getBirthdate();
                found = true;
                break;
            }

        }
    }
}