package com.SafetyNetAlert.SafetyNet.dto;

import com.SafetyNetAlert.SafetyNet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChildAlertDto {

    private String firstName;
    private String lastName;
    private int age;
    private List<Person> adults;
    private List<Person> children;
}
