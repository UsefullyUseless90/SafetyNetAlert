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
     *
     */

    public void initMedicalRecords(DataJson dataJson) throws IOException {

        List<MedicalRecord> records = new ArrayList<>();
        records = dataJson.getMedicalrecords();
        int i = 0;
        while(i< records.size()){
        for (MedicalRecord mR : dataJson.getMedicalrecords()) {
                if (this.getFirstName().equals(mR.getFirstName()) && this.getLastName().equals(mR.getLastName())) {
                    this.setMedications(mR.getMedications());
                    this.setAllergies(mR.getAllergies());
                    String naissance = mR.getBirthdate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(naissance, formatter);
                    this.setBirthdate(localDate);
                    break;
                }
            }
                break;
            }
        }
    }
