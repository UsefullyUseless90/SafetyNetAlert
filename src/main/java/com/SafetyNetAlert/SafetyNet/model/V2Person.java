package com.SafetyNetAlert.SafetyNet.model;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2Person {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    /**
     * @param p
     */
    public V2Person(Person p){
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.birthdate = null;
        this.phone = p.getPhone();
        this.email = p.getEmail();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();

    }
    /**
     *
     * @return
     */
    public V2Person initMedicalRecords() throws IOException {
        DataJson dataJson = new DataJson();
        JsonFileService json = new JsonFileService();
        dataJson = json.jsonReaderService();
        this.setMedications(new ArrayList<>());
        for (MedicalRecord mR : dataJson.getMedicalrecords()) {
            int m = 0;
            while (m < medications.size()) {
                getMedications().add(String.valueOf(mR));
            }
            break;
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
        this.setAllergies(new ArrayList<>());
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
                String naissance = mR.getBirthdate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate localDate = LocalDate.parse(naissance, formatter);
                this.setBirthdate(localDate);
                found = true;
                break;
            }
            break;
        }
        return null;
    }

}