package com.SafetyNetAlert.SafetyNet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class PersonInfoDto {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int age;
    private List<String> medications;
    private List<String> allergies;

}
