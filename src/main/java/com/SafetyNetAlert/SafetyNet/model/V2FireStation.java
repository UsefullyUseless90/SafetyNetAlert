package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class V2FireStation {

    private String id;
    private List<V2Family> familyList;

    public V2FireStation(FireStation firestation) {
        this.id = firestation.getStation();
        this.familyList = new ArrayList<>();
        this.getFamilyList().add(new V2Family(firestation.getAddress()));
    }

}
