package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChildList {

    private List<ChildAlert> childList;
    private List<CoveragePerson> otherMembers;

    public ChildList(V2FireStation v2FireStation) {
        this.childList = new ArrayList<>();

        for(V2Family v2Family : v2FireStation.getFamilyList()) {
            for (V2Person v2Person : v2Family.getPersonList()) {
                ChildAlert childAlert = new ChildAlert(v2Person, v2Family.getAddress());
                this.childList.add(childAlert);
            }
        }
    }
}

