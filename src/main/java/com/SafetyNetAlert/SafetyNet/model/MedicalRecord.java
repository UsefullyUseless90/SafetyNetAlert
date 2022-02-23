package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "medicalrecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "medications")
    private ArrayList<String> medications;
    @Column(name = "allergies")
    private ArrayList<String> allergies;

}
