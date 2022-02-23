package com.SafetyNetAlert.SafetyNet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class Child  {
    /**
     * FirstName.
     */
    private String firstName;
    /**
     * LastName.
     */
    private String lastName;
    /**
     * Age.
     */
    private int age;

    private List<Child> numberOfChildren;

}
