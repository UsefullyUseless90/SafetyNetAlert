package com.SafetyNetAlert.SafetyNet.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PeopleCoveredByFireStationDto {

private String address;
private String firstName;
private String lastName;
private String phone;
private int adults;
private int children;

private List<PersonInfoDto> personInfoDtos;

}
