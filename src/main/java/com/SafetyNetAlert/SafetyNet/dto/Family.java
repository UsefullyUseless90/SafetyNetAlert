package com.SafetyNetAlert.SafetyNet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data


public class Family {

    private String lastName;

    private List<Family> familyList;

}
