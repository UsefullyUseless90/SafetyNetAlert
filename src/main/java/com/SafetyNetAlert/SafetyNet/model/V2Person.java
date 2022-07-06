package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class V2Person {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private int age;
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
        this.age = -1;
        this.phone = p.getPhone();
        this.email = p.getEmail();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();

    }


    public void initMedicalRecords(MedicalRecord medicalRecord) throws IOException {
        this.setMedications(medicalRecord.getMedications());
        this.setAllergies(medicalRecord.getAllergies());
        //MM/JJ/AAAA
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(medicalRecord.getBirthdate());
        this.setBirthdate(localDate);

        LocalDate birthDate = this.getBirthdate();
        this.age = Period.between(birthDate, LocalDate.now()).getYears();

    }


}
