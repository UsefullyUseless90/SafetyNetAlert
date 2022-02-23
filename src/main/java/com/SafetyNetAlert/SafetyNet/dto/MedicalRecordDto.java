package com.SafetyNetAlert.SafetyNet.dto;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

}
