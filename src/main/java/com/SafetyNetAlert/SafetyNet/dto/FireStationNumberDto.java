package com.SafetyNetAlert.SafetyNet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class FireStationNumberDto {

    private int station;
    private  int age;
    private String address;
    private String houseHoldStationCoverage;
    private int personLivingAtTheSameAddress;

}
