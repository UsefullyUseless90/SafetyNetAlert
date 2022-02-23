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
public class PhoneAlertDto {

    private String phone;

    private List<String> phoneList;

    public void PhoneList(List<String> filteredPhoneList) {
    }
}
