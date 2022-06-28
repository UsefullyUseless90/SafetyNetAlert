package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChildList {

    private List<ChildAlert> childAlertList;

    public ChildList(V2FireStation v2FireStation) {
        this.childAlertList = new ArrayList<>();

        for(V2Family v2Family : v2FireStation.getFamilyList()) {
            for (V2Person v2Person : v2Family.getPersonList()) {
                ChildAlert childAlert = new ChildAlert(v2Person, v2Family.getAddress());
                this.childAlertList.add(childAlert);
            }
        }
    }
}

