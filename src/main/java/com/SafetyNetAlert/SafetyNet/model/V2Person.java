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
     */
    public void initMedicalRecords() throws IOException {
        DataJson dataJson = new DataJson();
        JsonFileService json = new JsonFileService();
        dataJson = json.jsonReaderService();
        List<MedicalRecord> healthData = new ArrayList<>();
        healthData = dataJson.getMedicalrecords();
        int i = 0;
        for (MedicalRecord mR : dataJson.getMedicalrecords()) {
            while (i < healthData.size()) {
                if (mR.getFirstName() == this.firstName && mR.getLastName() == this.lastName) {
                    mR.getMedications().add(getMedications().get(i));
                    mR.getAllergies().add(getAllergies().get(i));
                    String naissance = mR.getBirthdate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(naissance, formatter);
                    this.setBirthdate(localDate);
                    break;
                }
                break;
            }
            break;
        }
    }
}