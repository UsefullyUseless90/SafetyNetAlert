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

    private List <Person> numberOfChildren;
    private List<Person> numberOfAdults;
    private String firstName;
    private String lastName;
    private int age;
    private List<Person> family;
    private List<Person> children;
}
