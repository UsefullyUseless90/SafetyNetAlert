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
public class FloodStationDto {

    private String lastName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
    private List<Long> stationNumber;// that's a patch (kind of) this way there's no more pb with
                                        // the station list in createFloodStation(FireStationImpl) method.

}
